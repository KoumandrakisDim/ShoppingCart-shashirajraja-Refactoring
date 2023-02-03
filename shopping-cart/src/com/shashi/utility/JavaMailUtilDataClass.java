package com.shashi;

public class JavaMailUtilDataClass {

	private Session session;
	private String myAccountEmail;
	private String recipientEmail;
	private String subject;
	private String htmlTextMessage;
	
	public JavaMailUtilDataClass(Session sess, String myAccEmail, String recEmail, String sub, String htmlTextMsg) {
		session = sess;
		myAccountEmail = myAccEmail;
		recipientEmail = recEmail;
		subject = sub;
		htmlTextMessage = htmlTextMsg;
	}
	
	public Session getSession() {
		return session;
	}
	
	public String getMyAccountEmail() {
		return myAccountEmail;
	}
	
	public String getRecipientEmail() {
		return recipientEmail;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getHtmlTextMessage() {
		return htmlTextMessage;
	}
}
