package com.ttmeng.duron.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ttmeng.duron.utility.WeekSerializer;

@Entity
@Table(name="du_duron")
public class Duron implements Serializable {
	
	@Id
	private String id;//主键 date format basic iso
	private String era;//天干地支纪年
	private String lunar;//农历
	@JsonSerialize(using=WeekSerializer.class)
	private Integer week;//星期
	private Integer year;//年
	private Integer mouth;//月
	private Integer day;//日
	private String act;//行动、宜忌
	private Boolean taboo;//忌
	private String motto;//语录
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getEra() {
		return era;
	}



	public void setEra(String era) {
		this.era = era;
	}



	public String getLunar() {
		return lunar;
	}



	public void setLunar(String lunar) {
		this.lunar = lunar;
	}



	public Integer getWeek() {
		return week;
	}



	public void setWeek(Integer week) {
		this.week = week;
	}



	public Integer getYear() {
		return year;
	}



	public void setYear(Integer year) {
		this.year = year;
	}



	public Integer getMouth() {
		return mouth;
	}



	public void setMouth(Integer mouth) {
		this.mouth = mouth;
	}



	public Integer getDay() {
		return day;
	}



	public void setDay(Integer day) {
		this.day = day;
	}



	public String getAct() {
		return act;
	}



	public void setAct(String act) {
		this.act = act;
	}



	public Boolean getTaboo() {
		return taboo;
	}



	public void setTaboo(Boolean taboo) {
		this.taboo = taboo;
	}



	public String getMotto() {
		return motto;
	}



	public void setMotto(String motto) {
		this.motto = motto;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 2076298652342535977L;

}
