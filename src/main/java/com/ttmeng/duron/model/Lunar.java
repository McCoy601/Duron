package com.ttmeng.duron.model;

public class Lunar {
	private int year;
	private int month;
	private int day;
	private String zodiar;
	private String stemYear;
	private String stemMonth;
	private String stemDay;
	private String chsMonth;
	private String chsDay;
	
	public Lunar() {
	}
	/**
	 * 
	 * @param year 农历年
	 * @param month 农历月
	 * @param day 农历日
	 * @param zodiar 生肖
	 * @param stemYear 纪年
	 * @param stemMonth 纪月
	 * @param stemDay 纪日
	 */
	public Lunar(int year, int month, int day, String zodiar, String stemYear, String stemMonth, String stemDay) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.zodiar = zodiar;
		this.stemYear = stemYear;
		this.stemMonth = stemMonth;
		this.stemDay = stemDay;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getZodiar() {
		return zodiar;
	}

	public void setZodiar(String zodiar) {
		this.zodiar = zodiar;
	}

	public String getStemYear() {
		return stemYear;
	}

	public void setStemYear(String stemYear) {
		this.stemYear = stemYear;
	}

	public String getStemMonth() {
		return stemMonth;
	}

	public void setStemMonth(String stemMonth) {
		this.stemMonth = stemMonth;
	}

	public String getStemDay() {
		return stemDay;
	}

	public void setStemDay(String stemDay) {
		this.stemDay = stemDay;
	}
	public String getChsMonth() {
		return chsMonth;
	}
	public void setChsMonth(String chsMonth) {
		this.chsMonth = chsMonth;
	}
	public String getChsDay() {
		return chsDay;
	}
	public void setChsDay(String chsDay) {
		this.chsDay = chsDay;
	}

}
