package jwd.wafepa.repository;

import jwd.wafepa.model.Activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	// @Query("select a from Activity a where a.name = :name")
	// List<Activity> findByName(@Param("name") String name);

	// rec Paginisano znaci po stranicama

	Page<Activity> findAll(Pageable pageable);

	Page<Activity> findByNameLike(String name, Pageable pageable);

	Page<Activity> findByOrderByDateDesc(Pageable pageable);
	
	Page<Activity> findByHiddenFalse(Pageable pageable);
	
	Page<Activity> findByNameContainingAndHiddenFalse(String name, Pageable pageable);
}
