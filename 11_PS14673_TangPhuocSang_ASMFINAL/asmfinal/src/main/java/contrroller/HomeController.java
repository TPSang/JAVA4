package contrroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;

import com.fasterxml.jackson.databind.ObjectMapper;

import constant.SessionAttr;
import dto.UserDto;
import dto.VideoLikedInfo;
import entities.History;
import entities.User;
import entities.Video;
import implservice.HistorySeerviceImpl;
import implservice.StatsServiceImpl;
import implservice.UserServiceImpl;
import implservice.VideoServiceImpl;
import service.Historyservice;
import service.StatsService;
import service.UserService;
import service.VideoService;

@WebServlet({ "/admin", "/HomeController", "/index", "/language", "/favorites", "/home", "/admin/favorites2" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int VIDEO_MAX_PAGE_SIZE = 2;
	private VideoService videoService = new VideoServiceImpl();
	private Historyservice historyService = new HistorySeerviceImpl();
	private UserService userService = new UserServiceImpl();
	private StatsService statsService = new StatsServiceImpl();

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User)session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE)  {
			req.setAttribute("isAdmin", true);
			

		} 
		
		String path = req.getServletPath();
		switch (path) {
		case "/index":
			doGetIndex(req, resp);
			break;
		case "/favorites":
			doGetFavorites(session, req, resp);
			break;
		case "/history":
			doGetHistory(session, req, resp);
			break;
		case "/admin":
			getGetAdmin(session, req, resp);
			break;
		case "/admin/favorites2":
			doGetFavorites2(session, req, resp);
			break;
		
		

		
		default:
		doGetIndex(req, resp);
		}

	}

	private void getGetAdmin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User currentUser = (User)session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE)  {
			req.getRequestDispatcher("views/admin/home.jsp").forward(req, resp);
			

		}else {
			doGetIndex(req, resp);
		}
		
		
	}

	private void doGetFavorites2(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String videoHref = req.getParameter("href");
		List<UserDto> users = userService.findUsersLikedVideoByVideoHref(videoHref);
		if (users.isEmpty()) {
			resp.setStatus(400);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			String dataResponse = mapper.writeValueAsString(users);
			resp.setStatus(200);
			out.print(dataResponse);
			out.flush();
		}
	}

	private void doGetHome(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<VideoLikedInfo> videos = ((StatsServiceImpl) statsService).findVideoLikedInfo();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}

	private void doGetHistory(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);

		List<History> histories = historyService.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		histories.forEach(item -> videos.add(item.getVideoId()));
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/history.jsp").forward(req, resp);

	}

	private void doGetFavorites(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);

		List<History> histories = historyService.findByUserAndIsLiked(user.getUsername());
		List<Video> videos = new ArrayList<>();
		histories.forEach(item -> videos.add(item.getVideoId()));
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/favorites.jsp").forward(req, resp);
	}

	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage = (int)Math.ceil(countVideo.size()/(double)VIDEO_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		
		String pageNumber = req.getParameter("page");
		List<Video> videos;
		if(pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			 videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
			 req.setAttribute("currentPage", 1);
		} else {
			videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}

	private void doGetLanguage(HttpServletRequest req, HttpServletResponse respe) {
		String language = req.getParameter("lang");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
