package implservice;

import javax.servlet.ServletContext;

import entities.User;
import service.EmailService;
import utils.SendEmailUtil;

public class EmailServiceImpl implements EmailService {

	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertaiment";
	private static final String EMAIL_WELCOME_PASSWORD = "Online Entertainment - New password";
	
	@Override
	public void sendEmail(ServletContext context, User recipient, String type) {
		
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        
        try {
			String content = null;
			String subject = null;
			
			switch (type) {
			case "welcome":
				subject = "EMAIL_WELCOME_SUBJECT";
				content = "Dear "+recipient.getUsername()+", hope you have a good time!";
				break;

			case "forgot":
				subject = "EMAIL_WELCOME_PASSWORD";
				content = "Dear "+recipient.getUsername()+", your new password here: "+recipient.getPassword();
				break;
			default:
					subject = "Online Entertainment";
					content = "Maybe this email is wrong, don't care about it";
					break;
			}
			SendEmailUtil.sendEmail(host,port,user,pass,recipient,subject,content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
