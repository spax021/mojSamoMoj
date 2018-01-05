package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.User;

import org.springframework.data.domain.Page;

public interface UserService {

	User findOne(Long id);

	Page<User> findAll(int stranica, int Page);

	User save(User user);

	void remove(Long id) throws IllegalArgumentException;

	Page<User> findByEmail(String email, int stranica, int page);
	
	List<User> save(List<User> users);

	void remove(List<Long> ids);

}
