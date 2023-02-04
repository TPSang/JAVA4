package impldao;

import java.util.List;

import dao.AbstractDao;
import dao.VideoDao;
import entities.User;
import entities.Video;

public class VideoDaoImpl extends AbstractDao<Video> implements VideoDao {

	@Override
	public Video findById(Integer id) {
		
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHreft(String href) {
		String sql = "select o from Video o where o.href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		
		return super.findAll(Video.class, true, pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video update(Video entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video delete(Video entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
