package service;

import java.util.List;

import dto.UserDto;
import entities.User;

public interface UserService {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User login(String username, String password);
	User resetPassword(String email);
	List<User> findAll();
	List<User> findAll(int pageNumber,int pageSize);
	User create(String username,String password,String email);
	User update(User entity);
	User delete(String username);
	List<UserDto> findUsersLikedVideoByVideoHref(String href);
}
