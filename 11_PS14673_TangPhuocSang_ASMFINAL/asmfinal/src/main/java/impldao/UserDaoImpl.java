package impldao;

import java.util.List;
import java.util.Map;

import dao.AbstractDao;
import dao.UserDao;
import entities.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@Override
	public User findById(Integer id) {
		
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		String sql = "select o from User o where o.email = ?0";
		
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUsername(String username) {
		String sql = "select o from User o where o.username = ?0";
		
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		System.out.println(username +"-----" + password);
		String sql = "select o from User o where o.username = ?0 and o.password = ?1";
		return super.findOne(User.class, sql, username,password);
	}

	@Override
	public List<User> findAll() {
		
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> findAll(int pageNumber,int pageSize) {
		
		return super.findAll(User.class, true, pageNumber, pageSize);
	}

	@Override
	public User delete(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

//	public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params) {
//		
//		return super.callStored(NamedStored.FIND_USERS_LIKED_VIDEO_BY_VIDEO_HREF, params);
//	}

	@Override
	public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
