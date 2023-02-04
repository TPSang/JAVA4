package service;

import java.util.List;

import entities.Video;

public interface VideoService {
	Video findById(Integer id);
	Video findByHreft(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber,int pageSize);
	Video create(Video entity);
	Video update(Video entity);
	Video delete(String href);
}
