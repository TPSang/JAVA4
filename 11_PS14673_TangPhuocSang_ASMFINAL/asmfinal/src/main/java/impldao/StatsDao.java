package impldao;

import java.util.List;

import dto.VideoLikedInfo;
import entities.User;

public interface StatsDao {
	List<VideoLikedInfo> findVideoLikedInfo();
	
}
