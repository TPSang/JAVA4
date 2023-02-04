package dao;

import java.util.List;

import entities.History;

public interface HistoryDao {
	List<History> findUser(String username);
	List<History> findByUserAndIsLiked(String username);
	History findByUserAndVideoId(Integer userId, Integer videoId);
	History create(History entity);
	History update(History entity);
}
