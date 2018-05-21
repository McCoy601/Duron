package com.ttmeng.duron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ttmeng.duron.entity.Duron;

public interface DuronRepository extends JpaRepository<Duron, String> {
	
	@Query("select d from Duron d where d.id<=?1 order by id asc")
	List<Duron> findByBefore(String today);

}
