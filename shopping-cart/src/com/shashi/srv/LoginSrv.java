package com.shashi.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shashi.beans.UserBean;
import com.shashi.dao.UserDaoImpl;

/**
 * Servlet implementation class LoginSrv
 */
@WebServlet("/LoginSrv")
public class LoginSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginSrv() {
        super();
    }
    
    protected void loginAsAdmin(HttpServletRequest request, HttpServletResponse response, LoginInfo loginInfo) throws ServletException, IOException {
    	if(loginInfo.getPassword().equals("Admin") && loginInfo.getUsername().equals("Admin")) {
			//valid
			
			RequestDispatcher rd = request.getRequestDispatcher("adminViewProduct.jsp");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("username", loginInfo.getUsername());
			session.setAttribute("password", loginInfo.getPassword());
			session.setAttribute("usertype", loginInfo.getUserType());
			
			
			rd.forward(request, response);

			
		}
		else {
			//Invalid;
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			
			rd.include(request, response);
			loginInfo.getPrintWriter().println("<script>document.getElementById('message').innerHTML='"+loginInfo.getStatus()+"'</script>");
		}
    }
    
    protected void loginAsUser(HttpServletRequest request, HttpServletResponse response, LoginInfo loginInfo) throws ServletException, IOException {
    	UserDaoImpl udao = new UserDaoImpl();
		
		 status = udao.isValidCredential(userName, password);
		 
		 if(status.equalsIgnoreCase("valid")) {
			 //valid user
			 
			 UserBean user = udao.getUserDetails(userName, password);
			 
			 HttpSession session = request.getSession();
			 
			 session.setAttribute("userdata", user);
			 
			 session.setAttribute("username", loginInfo.getUsername());
			 session.setAttribute("password", loginInfo.getPassword());
			 session.setAttribute("usertype", loginInfo.getUserType());
			 
			 RequestDispatcher rd = request.getRequestDispatcher("userHome.jsp");
			 
			 rd.forward(request, response);
			 
		 }
		 else {
			 //invalid user;
			 
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
				
			rd.include(request, response);
			
			loginInfo.getPrintWriter().println("<script>document.getElementById('message').innerHTML='"+loginInfo.getStatus()+"'</script>");
			 
			 
		 }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String status = "Login Denied! Invalid Username or password.";
		
		LoginInfo loginInfo = new LoginInfo(request.getParameter("username"), request.getParameter("password"),
				request.getParameter("usertype"), response.getWriter(), status);
		
		if(userType.equals("admin")){  //Login as Admin
			loginAsAdmin(request, response, loginInfo);
		}
		else {
			loginAsUser(request, response, loginInfo);
		}
	}

}
