package impldao;

import java.util.ArrayList;
import java.util.List;

import dao.AbstractDao;
import dto.VideoLikedInfo;
import entities.User;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		String sql = "select v.id, v.title,v.href, sum(cast(h.isLiked as int)) as totalLike"
				+ " from	video v left join history h on v.id = h.videoId"
				+ " where v.isActive = 1"
				+ " group by v.id,v.title,v.href"
				+ " order by sum(cast(h.isLiked as int)) desc";
		List<Object[]> objects = super.findManyNativeQuery(sql);
		List<VideoLikedInfo> result = new ArrayList<>();
		objects.forEach(object -> {
			VideoLikedInfo videoLikedInfo = setDataVideoLikedInfo(object);
			
			result.add(videoLikedInfo);
		});
		return result;
	}

	private VideoLikedInfo setDataVideoLikedInfo(Object[] object) {
		VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
		videoLikedInfo.setVideoId((Integer)object[0]);
		videoLikedInfo.setTitle((String)object[1]);
		videoLikedInfo.setHref((String)object[2]);
		videoLikedInfo.setTotalLike((Integer)object[3]==null ? 0 : (Integer) object[3]);
		return videoLikedInfo;
	}
}
