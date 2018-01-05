package jwd.wafepa.service.impl;

import java.util.List;

import jwd.wafepa.model.User;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JpaUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> findAll(int stranica, int page) {
		if (stranica == -1){
			stranica = Integer.MAX_VALUE;
		}
		return userRepository.findAll(new PageRequest(page, stranica));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		User user = userRepository.findOne(id);
		if(user == null){
			throw new IllegalArgumentException("Tried to remove nonexistant activity id:" + id);
		}
		userRepository.delete(user);
	}

	@Override
	public List<User> save(List<User> users) {
		return userRepository.save(users);
	}

	@Override
	public void remove(List<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<User> findByEmail(String email, int stranica, int page) {
		return userRepository.findByEmailLike("%" + email + "%", new PageRequest(page, stranica));
	}

}
