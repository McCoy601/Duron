package com.ttmeng.duron.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ttmeng.duron.entity.Duron;
import com.ttmeng.duron.repository.DuronRepository;
import com.ttmeng.duron.service.DuronService;

@Service
public class DuronServiceImpl implements DuronService {
	
	@Autowired
	DuronRepository duronRepo;
	
	@Override
	public List<Duron> findBefore() {
		LocalDate now=LocalDate.now();
		
		return duronRepo.findByBefore(now.format(DateTimeFormatter.BASIC_ISO_DATE));
	}

	@Override
	public Duron saveOrUpdate(Duron duron) throws IllegalArgumentException {
		if (duron == null||StringUtils.isEmpty(duron.getId())) {
			throw new IllegalArgumentException("duron不能为空");
		}
		
		return duronRepo.save(duron);
	}

	@Override
	public Duron get(String id) {
		return duronRepo.getOne(id);
	}

}
