package implservice;

import java.sql.Timestamp;
import java.util.List;

import dao.HistoryDao;
import entities.History;
import entities.User;
import entities.Video;
import impldao.HistoryDaoImpl;
import service.Historyservice;
import service.VideoService;

public class HistorySeerviceImpl implements Historyservice {
	private HistoryDao dao;
	private VideoService videoService = new VideoServiceImpl();
	
	public HistorySeerviceImpl() {
		dao = new HistoryDaoImpl();
	}
	@Override
	public List<History> findByUser(String username) {
		
		return ((Historyservice) dao).findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		
		return dao.findByUserAndIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		
		return dao.findByUserAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if(existHistory == null) {
			existHistory = new History();
			existHistory.setUserId(user);
			existHistory.setVideoId(video);
			existHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
			existHistory.setIsLiked(Boolean.FALSE);
			return dao.create(existHistory);
		}
		
		return existHistory;
	}

	@Override
	public boolean updateLikeOrUnLike(User user, String videoHref) {
		System.out.println(user+"===="+videoHref);
		Video video = videoService.findByHreft(videoHref);
		History exisHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if(exisHistory == null) {
			exisHistory = create(user,video);
		}
		System.out.println(exisHistory.getIsLiked()+"===="+videoHref);
			if(exisHistory.getIsLiked() == Boolean.FALSE) {
				exisHistory.setIsLiked(true);
				exisHistory.setLikeddDate(new Timestamp(System.currentTimeMillis()));
			} else {
				exisHistory.setIsLiked(Boolean.FALSE);
				exisHistory.setLikeddDate(null);
			}
			exisHistory = dao.update(exisHistory);
			
		
		
		return true;
	}

}
