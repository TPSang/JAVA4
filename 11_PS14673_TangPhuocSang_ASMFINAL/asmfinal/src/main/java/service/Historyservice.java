package service;

import java.util.List;

import entities.History;
import entities.User;
import entities.Video;

public interface Historyservice {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked(String username);
	History findByUserIdAndVideoId(Integer userId,Integer videoId);
	History create(User user,Video video);
	boolean updateLikeOrUnLike(User user,String videoHref);
}
