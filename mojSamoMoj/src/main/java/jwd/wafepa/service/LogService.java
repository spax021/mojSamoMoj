package jwd.wafepa.service;

import jwd.wafepa.model.Log;

import org.springframework.data.domain.Page;

public interface LogService {
	
	Log save(Log log);
	
	Page<Log> findAll(int stranica, int page);

}
