package com.shashi.srv;

import java.io.PrintWriter;

public class LoginInfo {

	private String userName;
	private String password;
	private String userType;
	private PrintWriter printWriter;
	private String status;
	
	public LoginInfo(String _userName, String _password, String _userType, PrintWriter _printWriter, String _status) {
		userName = _userName;
		password = _password;
		userType = _userType;
		printWriter = _printWriter;
		status = _status;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public PrintWriter getPrintWriter() {
		return userName;
	}
	
	public String getStatus() {
		return status;
	}
}
