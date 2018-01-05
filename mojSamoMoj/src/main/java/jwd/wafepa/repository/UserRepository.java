package jwd.wafepa.repository;

import jwd.wafepa.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable pageable);
	Page<User> findByEmailLike(String email, Pageable pageable);
	
}
