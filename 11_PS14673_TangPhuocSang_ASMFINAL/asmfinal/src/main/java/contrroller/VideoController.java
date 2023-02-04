package contrroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import constant.SessionAttr;
import entities.History;
import entities.User;
import entities.Video;
import implservice.HistorySeerviceImpl;
import implservice.VideoServiceImpl;
import service.Historyservice;
import service.VideoService;

@WebServlet({"/video","/VideoController"})
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private VideoService videoService = new VideoServiceImpl();
	private Historyservice historyService = new HistorySeerviceImpl();
    public VideoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		
		HttpSession session = req.getSession();
		switch (actionParam) {
		case "watch":
			doGetWatch(session,href,req,resp);
			break;
		case "like":
			doGetLike(session,href,req,resp);
			break;
		}
	}

	private void doGetLike(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");
		User currentUser = (User)session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result = historyService.updateLikeOrUnLike(currentUser, href);
		if(result == true) {
			resp.setStatus(204);//thanh cong k bao data
		} else {
			resp.setStatus(400);
		}
	}


	private void doGetWatch(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = videoService.findByHreft(href);
		
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null) {
			History history = historyService.create(currentUser, video);
			System.out.println("history = " + history.getIsLiked());
			req.setAttribute("flagLikedBtn", history.getIsLiked());
		}
		
		req.setAttribute("video", video);
		req.getRequestDispatcher("/views/user/video-detail.jsp").forward(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
