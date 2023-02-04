package dao;

import java.util.List;

import entities.Video;

public interface VideoDao {
	Video findById(Integer id);
	Video findByHreft(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber,int pageSize);
	Video create(Video entity);
	Video update(Video entity);
	Video delete(Video entity);
}
