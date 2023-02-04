package impldao;

import java.util.List;

import dao.AbstractDao;
import dao.HistoryDao;
import entities.History;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao{

	@Override
	public List<History> findUser(String username) {
		String sql = "select o from History o where o.user.username=?0 and o.video.isActive=1"
				+ " order by o.viewedDate desc";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		String sql = "select o from History o where o.user.username=?0 and o.isLiked=1"
				+ " and o.video.isActive=1"
				+ " order by o.viewedDate desc";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findByUserAndVideoId(Integer userId, Integer videoId) {
		String sql = "select o from History o where o.user.id = ?0 and o.video.id = ?1"
				+ " and o.video.isActive = 1";
		return super.findOne(History.class, sql, userId,videoId);
	}
	
}
