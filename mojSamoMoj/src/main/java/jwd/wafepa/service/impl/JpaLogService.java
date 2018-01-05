package jwd.wafepa.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Log;
import jwd.wafepa.repository.LogRepository;
import jwd.wafepa.service.LogService;

@Transactional
@Service
public class JpaLogService implements LogService {

	@Autowired
	private LogRepository logRepository;
	
	@Override
	public Log save(Log log) {
		return logRepository.save(log);
	}

	@Override
	public Page<Log> findAll(int pageSize, int page) {
		if (pageSize == -1){
			pageSize = Integer.MAX_VALUE;
		}
		return logRepository.findAll(new PageRequest(page, pageSize));
	}

}
