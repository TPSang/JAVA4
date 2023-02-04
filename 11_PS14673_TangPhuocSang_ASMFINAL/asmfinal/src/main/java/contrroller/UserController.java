package contrroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entities.User;
import implservice.EmailServiceImpl;
import implservice.UserServiceImpl;
import service.EmailService;
import service.UserService;

@WebServlet({"/login","/logout","/register","/forgot-password","/changePass"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch(path) {
		case "/login":
			doGetLogin(req,resp);
			break;
		case "/register":
			doGetRegister(req,resp);
			break;
		case "/logout":
			doGetLogout(session,req,resp);
			break;
//		case "/forgot-password":
//			doGetForgot(session,req,resp);
//			break;
		}
	}

	private void doGetForgot(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/user/forgot.jsp").forward(req, resp);
	}

	private void doGetLogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}

	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/user/register.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		System.out.println("path="+path);
		switch(path) {
		case "/login":
			doPostLogin(session,req,resp);
			break;
		case "/register":
			doPostRegister(session,req,resp);
			break;
//		case "/forgot-password":
//			doPostForgotPass(req,resp);
//			break;
//		case "changePass":
//			doPostChangePass(session,req,resp);
//			break;
		}
	}
	private void doPostChangePass(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");
		String currentPass = req.getParameter("currentPass");
		String newPass = req.getParameter("newPass");
		
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if(currentUser.getPassword().equals(currentUser)) {
			currentUser.setPassword(newPass);
			User updatedUser = userService.update(currentUser);
			if(updatedUser != null) {
				session.setAttribute(SessionAttr.CURRENT_USER, updatedUser);
				resp.setStatus(204);
			}else {
				resp.setStatus(400);
			}
		} else {
			resp.setStatus(400);
		} 
	}

	private void doPostForgotPass(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");
		String email = req.getParameter("email");
		User userWithNewPass = userService.resetPassword(email);
		if(userWithNewPass != null) {
			emailService.sendEmail(getServletContext(), userWithNewPass, "forgot");
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}

	private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		System.out.println("Regist " + username + "---" + password +"----"+ email);
		
		User user = userService.create(username, password,email);
		
		if(user != null) {
			emailService.sendEmail(getServletContext(), user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("register");
//			req.getRequestDispatcher("views/user/index.jsp").forward(req, resp);
		}
	}

	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("views/user/login.jsp").forward(req, resp);
		
	}
	
	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User user = userService.login(username, password);
			
			if(user != null) {
				session.setAttribute(SessionAttr.CURRENT_USER, user);
				resp.sendRedirect("index");
			} else {
				resp.sendRedirect("login");
//				req.getRequestDispatcher("views/user/index.jsp").forward(req, resp);
			}
		}

}
