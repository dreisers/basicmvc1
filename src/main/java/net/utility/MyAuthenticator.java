package net.utility;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	//?‚¬?š©?•˜ê³ ì ?•˜?Š” ë©”ì¼?„œë²„ì—?„œ ?¸ì¦ë°›?? ê³„ì •/ë¹„ë²ˆ
  
	private PasswordAuthentication pa;

	public MyAuthenticator() {
	  pa = new PasswordAuthentication("soldesk@pretyimo.cafe24.com", "soldesk6901");
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

	
	
		
	
	
}//class end
