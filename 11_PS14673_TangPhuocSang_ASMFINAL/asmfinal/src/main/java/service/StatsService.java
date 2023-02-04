package service;

import java.util.List;

import dto.VideoLikedInfo;

public interface StatsService {

	List<VideoLikedInfo> findVideoLikedInfo();

}
