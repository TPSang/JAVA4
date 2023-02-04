package implservice;

import java.util.List;

import dao.VideoDao;
import entities.Video;
import impldao.VideoDaoImpl;
import service.VideoService;

public class VideoServiceImpl implements VideoService {

	private VideoDao dao;
	public VideoServiceImpl() {
		dao = new VideoDaoImpl();
	}
	
	@Override
	public Video findById(Integer id) {
		
		return dao.findById(id);
	}

	@Override
	public Video findByHreft(String href) {
		
		return dao.findByHreft(href);
	}

	@Override
	public List<Video> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		entity.setIsActive(Boolean.TRUE);
		entity.setViews(0);
		entity.setShares(0);
		return dao.create(entity);
	}

	@Override
	public Video update(Video entity) {
		
		return dao.update(entity);
	}

	@Override
	public Video delete(String href) {
		Video entity = findByHreft(href);
		entity.setIsActive(Boolean.FALSE);
		return dao.update(entity);
	}
	
}
