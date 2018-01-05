package jwd.wafepa.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.groups.Default;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Log;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.LogService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.LogDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/logs")
public class ApiLogController {

	@Autowired
	private LogService logService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getLogs(
			@RequestParam(value = "activityId", required = false) Long activityId,
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "logId", required = false) Long logId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		List<LogDTO> logs = new ArrayList<>();
		if (page == null){
			page = 0;
		}
		if (pageSize == null || pageSize == 0){
			pageSize = 5;
		}
		
		int totalPages = 0;
		Page<Log> logsPage;
		
		if (activityId != null) {
			Activity activity = activityService.findOne(activityId);
			for (Log log : activity.getLogs()) {
				logs.add(new LogDTO(log));
			}
		} else if(userId != null) {
			User user = userService.findOne(userId);
			for (Log log : user.getLogs()) {
				logs.add(new LogDTO(log));
			}
		} else{
			logsPage = logService.findAll(page, pageSize);
			for (Log log : logsPage){
				logs.add(new LogDTO(log));
			}
			totalPages = logsPage.getTotalPages();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("total-pages", "" + totalPages);
		}
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<LogDTO> addLog(@RequestBody LogDTO logDto) {

		Log log = new Log();
		log.setDate(logDto.getDate());
		log.setDuration(logDto.getDuration());
		Activity activity = activityService.findOne(logDto.getActivity()
				.getId());
		log.setActivity(activity);
		User user = userService.findOne(logDto.getUser().getId());
		log.setUser(user);

		Log logPersisted = logService.save(log);

		return new ResponseEntity<>(new LogDTO(logPersisted),
				HttpStatus.CREATED);
	}

}
