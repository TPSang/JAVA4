package implservice;

import java.util.List;

import dto.VideoLikedInfo;
import impldao.StatsDao;
import impldao.StatsDaoImpl;
import service.StatsService;

public class StatsServiceImpl implements StatsService {
	private StatsDao statsDao;
	public StatsServiceImpl() {
		statsDao = new StatsDaoImpl();
		
	}
	public List<VideoLikedInfo> findVideoLikedInfo() {
		return statsDao.findVideoLikedInfo();
		
	}
}
