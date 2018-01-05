package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.repository.ActivityRepository;
import jwd.wafepa.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JpaActivityService implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity findOne(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Page<Activity> findAll(int stranica, int page) {
		if (stranica == -1){
			stranica = Integer.MAX_VALUE;
		}
		return activityRepository.findByOrderByDateDesc(new PageRequest(page, stranica));
	}

	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Activity activity = activityRepository.findOne(id);
		if (activity == null) {
			throw new IllegalArgumentException("Tried to remove nonexistant activity id:" + id);
		}
		activityRepository.delete(activity);
	}

	@Override
	public Page<Activity> findByName(String name, int stranica, int page) {
		
		return activityRepository.findByNameLike("%" + name + "%", new PageRequest(page, stranica));
	}

	@Override
	public Page<Activity> getActivityLogs(Long id) {
		Activity activity = findOne(id);
		if(activity == null){
			return new PageImpl<Activity>(new ArrayList<Activity>());
		}
		return null;
	}

	@Override
	public Page<Activity> findAllNotHidden(Integer page, Integer stranica) {
		return activityRepository.findByHiddenFalse(new PageRequest(page, stranica));
	}

	@Override
	public Page<Activity> findByNameNotHidden(String name, Integer page, Integer stranica) {
		return activityRepository.findByNameContainingAndHiddenFalse(name, new PageRequest(page, stranica));
	}

	@Override
	public Page<Activity> findAllByOrderByCreationDateAsc(Integer page, Integer stranica) {
		return activityRepository.findByOrderByDateDesc(new PageRequest(page, stranica));
	}
	
}
