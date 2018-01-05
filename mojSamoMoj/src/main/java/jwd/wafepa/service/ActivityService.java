package jwd.wafepa.service;

import jwd.wafepa.model.Activity;

import org.springframework.data.domain.Page;

public interface ActivityService {

	Activity findOne(Long id);

	Page<Activity> findAll(int stranica, int page);

	Activity save(Activity activity);

	void remove(Long id) throws IllegalArgumentException;

	Page<Activity> findByName(String name, int stranica, int page);

	Page<Activity> findAllNotHidden(Integer page, Integer stranica);

	Page<Activity> getActivityLogs(Long id);

	Page<Activity> findByNameNotHidden(String name, Integer page, Integer stranica);

	Page<Activity> findAllByOrderByCreationDateAsc(Integer page, Integer stranica);

}
