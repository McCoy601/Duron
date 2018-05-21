package com.ttmeng.duron.service;

import java.util.List;

import com.ttmeng.duron.entity.Duron;

public interface DuronService {
	
	/**
	 * 获取今天之前（含今天）所有日历项
	 * @return
	 */
	public List<Duron> findBefore();
	
	/**
	 * 保存或更新
	 * @param duron
	 * @return
	 */
	public Duron saveOrUpdate(Duron duron) throws IllegalArgumentException;
	
	public Duron get(String id);

}
