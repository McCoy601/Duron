package com.ttmeng.duron.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttmeng.duron.entity.Duron;
import com.ttmeng.duron.model.Lunar;
import com.ttmeng.duron.service.DuronService;
import com.ttmeng.duron.utility.LunarUtil;

@RestController
@RequestMapping("/duron")
public class DuronController {
	
	@Autowired
	DuronService duronSvc;

	@GetMapping
	public List<Duron> index() {
		return duronSvc.findBefore();
	}
	
	@GetMapping(value = "/{id}")
	public Duron get(@PathVariable String id) {
		return duronSvc.get(id);
	}
	
	@PostMapping
	public Duron post(Duron duron) {
		if (duron == null) {
			return null;
		}
		LocalDate date=LocalDate.of(duron.getYear(), duron.getMouth(), duron.getDay());
		duron.setId(date.format(DateTimeFormatter.BASIC_ISO_DATE));
		duron.setWeek(date.getDayOfWeek().getValue());
		
		Lunar lunar=LunarUtil.of(date);
		duron.setEra(lunar.getStemYear()+"["+lunar.getZodiar()+"]年 "+lunar.getStemMonth()+"月 "+lunar.getStemDay()+"日");
		duron.setLunar(lunar.getChsMonth()+" "+lunar.getChsDay());
		
		return duronSvc.saveOrUpdate(duron);
	}
	
	@PutMapping(value="/{id}")
	public Duron put(@PathVariable String id,Duron duron) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		
		duron.setId(id);
		
		return duronSvc.saveOrUpdate(duron);
	}


}
