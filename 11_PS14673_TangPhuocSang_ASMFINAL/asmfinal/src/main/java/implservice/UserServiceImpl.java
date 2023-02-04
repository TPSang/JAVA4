package implservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserDao;
import dto.UserDto;
import entities.User;
import impldao.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	@Override
	public User findById(Integer id) {
		
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		
		return dao.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		
		return dao.findByUsernameAndPassword(username, password);
	}

	@Override
	public User resetPassword(String email) {
		User existUser = findByEmail(email);
		if( existUser != null) {
			//(random * (MAX - MIN) +1)+min
			String newPass = String.valueOf((int)(Math.random()) * ((9999 - 1000) +1)) + 1000;
			existUser.setPassword(newPass);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User create(String username, String password, String email) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean.FALSE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		User user = dao.findByUsername(username);
		user.setIsActive(Boolean.FALSE);
		return dao.update(user);
	}
	@Override
	public List<UserDto> findUsersLikedVideoByVideoHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href);
		List<User> users = dao.findUsersLikedVideoByVideoHref(params);
		List<UserDto> result = new ArrayList<>();
		users.forEach(user -> {
			UserDto dto = new UserDto();
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			result.add(dto);
		});
		
		return result;
	}

}
