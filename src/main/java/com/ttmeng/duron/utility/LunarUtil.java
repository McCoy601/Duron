package com.ttmeng.duron.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ttmeng.duron.model.Lunar;

/**
 * 农历工具
 * @see http://blog.jjonline.cn/userInterFace/173.html
 * @author iwang
 *
 */
public class LunarUtil {
	
	/**
	 * 农历数据
	 */
	private static final int[] LUNAR_INFO= {
			0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,//1900-1909
            0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,//1910-1919
            0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,//1920-1929
            0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,//1930-1939
            0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,//1940-1949
            0x06ca0,0x0b550,0x15355,0x04da0,0x0a5b0,0x14573,0x052b0,0x0a9a8,0x0e950,0x06aa0,//1950-1959
            0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,//1960-1969
            0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b6a0,0x195a6,//1970-1979
            0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,//1980-1989
            0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,//1990-1999
            0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,//2000-2009
            0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,//2010-2019
            0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,//2020-2029
            0x05aa0,0x076a3,0x096d0,0x04afb,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,//2030-2039
            0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0,//2040-2049
            0x14b63,0x09370,0x049f8,0x04970,0x064b0,0x168a6,0x0ea50,0x06b20,0x1a6c4,0x0aae0,//2050-2059
            0x0a2e0,0x0d2e3,0x0c960,0x0d557,0x0d4a0,0x0da50,0x05d55,0x056a0,0x0a6d0,0x055d4,//2060-2069
            0x052d0,0x0a9b8,0x0a950,0x0b4a0,0x0b6a6,0x0ad50,0x055a0,0x0aba4,0x0a5b0,0x052b0,//2070-2079
            0x0b273,0x06930,0x07337,0x06aa0,0x0ad50,0x14b55,0x04b60,0x0a570,0x054e4,0x0d160,//2080-2089
            0x0e968,0x0d520,0x0daa0,0x16aa6,0x056d0,0x04ae0,0x0a9d4,0x0a2d0,0x0d150,0x0f252,//2090-2099
            0x0d520};
	
	/**
	 * 24节气速查表
	 */
	private static final String[] SOLAR_SERM_INFO= {
			"9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f",
            "97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
            "97bcf97c359801ec95f8c965cc920f","97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa",
            "97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd0b06bdb0722c965ce1cfcc920f",
            "b027097bd097c36b0b6fc9274c91aa","9778397bd19801ec9210c965cc920e","97b6b97bd19801ec95f8c965cc920f",
            "97bd09801d98082c95f8e1cfcc920f","97bd097bd097c36b0b6fc9210c8dc2","9778397bd197c36c9210c9274c91aa",
            "97b6b97bd19801ec95f8c965cc920e","97bd09801d98082c95f8e1cfcc920f","97bd097bd097c36b0b6fc9210c8dc2",
            "9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec95f8c965cc920e","97bcf97c3598082c95f8e1cfcc920f",
            "97bd097bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec9210c965cc920e",
            "97bcf97c3598082c95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
            "97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722",
            "9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f",
            "97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
            "97bcf97c359801ec95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
            "97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd097bd07f595b0b6fc920fb0722",
            "9778397bd097c36b0b6fc9210c8dc2","9778397bd19801ec9210c9274c920e","97b6b97bd19801ec95f8c965cc920f",
            "97bd07f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c920e",
            "97b6b97bd19801ec95f8c965cc920f","97bd07f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2",
            "9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec9210c965cc920e","97bd07f1487f595b0b0bc920fb0722",
            "7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
            "97bcf7f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
            "97b6b97bd19801ec9210c965cc920e","97bcf7f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",
            "9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf7f1487f531b0b0bb0b6fb0722",
            "7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
            "97bcf7f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
            "97b6b97bd19801ec9210c9274c920e","97bcf7f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",
            "9778397bd097c36b0b6fc9210c91aa","97b6b97bd197c36c9210c9274c920e","97bcf7f0e47f531b0b0bb0b6fb0722",
            "7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c920e",
            "97b6b7f0e47f531b0723b0b6fb0722","7f0e37f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2",
            "9778397bd097c36b0b70c9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e37f1487f595b0b0bb0b6fb0722",
            "7f0e397bd097c35b0b6fc9210c8dc2","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721",
            "7f0e27f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
            "97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",
            "9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722",
            "7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721",
            "7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9274c91aa",
            "97b6b7f0e47f531b0723b0787b0721","7f0e27f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",
            "9778397bd097c36b0b6fc9210c91aa","97b6b7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722",
            "7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c8dc2","977837f0e37f149b0723b0787b0721",
            "7f07e7f0e47f531b0723b0b6fb0722","7f0e37f5307f595b0b0bc920fb0722","7f0e397bd097c35b0b6fc9210c8dc2",
            "977837f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0721","7f0e37f1487f595b0b0bb0b6fb0722",
            "7f0e397bd097c35b0b6fc9210c8dc2","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
            "7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","977837f0e37f14998082b0787b06bd",
            "7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",
            "977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722",
            "7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
            "7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14998082b0787b06bd",
            "7f07e7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",
            "977837f0e37f14998082b0723b06bd","7f07e7f0e37f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722",
            "7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b0721",
            "7f07e7f0e47f531b0723b0b6fb0722","7f0e37f1487f595b0b0bb0b6fb0722","7f0e37f0e37f14898082b0723b02d5",
            "7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e37f1487f531b0b0bb0b6fb0722",
            "7f0e37f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
            "7f0e37f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b072297c35","7ec967f0e37f14998082b0787b06bd",
            "7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b072297c35",
            "7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722",
            "7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f149b0723b0787b0721",
            "7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0723b06bd",
            "7f07e7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722","7f0e37f0e366aa89801eb072297c35",
            "7ec967f0e37f14998082b0723b06bd","7f07e7f0e37f14998083b0787b0721","7f0e27f0e47f531b0723b0b6fb0722",
            "7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14898082b0723b02d5","7f07e7f0e37f14998082b0787b0721",
            "7f07e7f0e47f531b0723b0b6fb0722","7f0e36665b66aa89801e9808297c35","665f67f0e37f14898082b0723b02d5",
            "7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e36665b66a449801e9808297c35",
            "665f67f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
            "7f0e36665b66a449801e9808297c35","665f67f0e37f14898082b072297c35","7ec967f0e37f14998082b0787b06bd",
            "7f07e7f0e47f531b0723b0b6fb0721","7f0e26665b66a449801e9808297c35","665f67f0e37f1489801eb072297c35",
            "7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722"};
	
	/**
	 * 公历每月天数
	 */
	private final static int[] SOLAR_MONTH= {31,28,31,30,31,30,31,31,30,31,30,31};
	
	/**
	 * 天干
	 */
	private final static String[] CELESTIAL_STEM= {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
	
	/**
	 * 地支
	 */
	private final static String[] TERRESTRIAL_BRANCH= {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
	
	/**
	 * 生肖
	 */
	private final static String[] ZODIAC= {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
	
	/**
	 * 二十四节气
	 */
	private final static String[] SOLAR_TERM= {"小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至"};
	
	/**
	 * 农历日期
	 */
	private final static String[] LUNAR_DATE= {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十", "卅一"};	
	
	/**
	 * 农历月份
	 */
	private final static String[] LUNAR_MONTH= {"正","二","三","四","五","六","七","八","九","十","冬","腊"};
	
	//起始年份及截止年份
	private final static int MIN_YEAR=1900,MAX_YEAR=2100;
	
	
	public static Lunar of(LocalDate date) {
		long offset= LocalDate.of(1900, 1, 31).until(date, ChronoUnit.DAYS);
		int year=1900,month=1,day=1;//农历年月日
		int temp=0;
		for (int i = 1900; i < 2101; i++) {
			temp=days4Year(i);
			if (temp>offset) {
				year=i;
				break;
			}else {
				offset-=temp;
			}
		}
		
		int leapMonth=leapMonth(year);
		boolean isLeap=false;
		temp=0;
		for (int i = 0; i < 13; i++) {
			if (leapMonth>0&&i==(leapMonth+1)&&isLeap==false) {
				--i;
				isLeap=true;
				temp=leapDays(year);
			}else {
				temp=days4Month(year, i);
			}
			
			if (isLeap&&i==(leapMonth+1)) {
				isLeap=false;
			}
			
			if (offset>temp) {
				offset-=temp;
			}else {
				month=i+1;
				break;
			}
		}
		
		/**
		 * 闰月处理
		 */
		if (offset==0&&leapMonth>0&&month==(leapMonth+1)) {
			if (isLeap) {
				isLeap=false;
			}else {
				isLeap=true;
				month--;
			}
		}
		
		day=(int)offset+1;
		
		String stemYear=lunarToStemYear(year);
		LocalDate coldTerm=termOnDate(date.getYear(), date.getMonthValue()*2-1);
		
		String stemMonth;
		if (date.getDayOfMonth()<coldTerm.getDayOfMonth()) {
			
			stemMonth=stem4Offset((date.getYear()-MIN_YEAR)*12+date.getMonthValue()+11);
		}else {
			stemMonth=stem4Offset((date.getYear()-MIN_YEAR)*12+date.getMonthValue()+12);
		}
		
		int dayOffset=(int)date.of(1900, 1, 1).until(date, ChronoUnit.DAYS)+10;//10:相对甲子偏移量
		String stemDay=stem4Offset(dayOffset);
		
		Lunar lunar = new Lunar();
		lunar.setYear(year);
		lunar.setMonth(month);
		lunar.setDay(day);
		lunar.setStemYear(stemYear);
		lunar.setStemMonth(stemMonth);
		lunar.setStemDay(stemDay);
		lunar.setZodiar(zodiac(year));
		lunar.setChsMonth(toChsMonth(month));
		lunar.setChsDay(toChsDay(day));
		
		return lunar;
		
	}
	
	/**
	 * 计算闰月所在月
	 * @param year 农历年 1900-2100
	 * @return 0-12,若为0，则表示无闰月
	 */
	private static int leapMonth(int year) throws IllegalArgumentException {
		if (year<MIN_YEAR||year>MAX_YEAR) {
			throw new IllegalArgumentException("year [1900,2100]");
		}
		return LUNAR_INFO[year-MIN_YEAR]&0xf;
	}
	
	/**
	 * 计算闰月的天数
	 * @param year
	 * @return 0/29/30 无闰月则返回0
	 */
	private static int leapDays(int year) {
		if (leapMonth(year)==0) {
			return 0;
		}
		
		return (LUNAR_INFO[year-MIN_YEAR]&0x10000)==0?29:30;
	}
	
	/**
	 * 计算一年的天数
	 * @param year 农历年
	 * @return
	 */
	private static int days4Year(int year) {
		int normalDays=348;//正常年天数
		
		for (int i =0x8000; i>0x8; i>>=1) {
			normalDays+=(LUNAR_INFO[year-MIN_YEAR]&i)==0?0:1;			
		}
		
		return normalDays+leapDays(year);
	}
	
	/**
	 * 计算农历某年 某月的总天数（非闰月）
	 * @param year
	 * @param month
	 * @return
	 */
	private static int days4Month(int year,int month) {
		return (LUNAR_INFO[year-1900]&(0x10000>>month))==0?29:30;
	}
	
	/**
	 * 计算公历月总天数
	 * @param year
	 * @param month
	 * @return
	 */
	private static int days4SolarMonth(int year,int month) {
		if (month==2) {
			return (((year%4==0)&&(year%100!=0))||(year%400==0))?29:28;
		}
		return SOLAR_MONTH[month-1];
	}
	
	/**
	 * 农历年转干支纪年
	 * @param year
	 * @return
	 */
	private  static String lunarToStemYear(int year) {
		return CELESTIAL_STEM[(year-4)%10]+TERRESTRIAL_BRANCH[(year-4)%12];
	}
	
	/**
	 * 根据偏移量（与甲子年）获取干支纪年
	 * @param offset
	 * @return
	 */
	private static String stem4Offset(int offset) {
		return CELESTIAL_STEM[offset%10]+TERRESTRIAL_BRANCH[offset%12];
	}
	
	/**
	 * 计算节气所在的公历年月日
	 * @param solarYear 公历年
	 * @param termIndex 节气顺序，从小寒开始[1,24]
	 * @return
	 */
	public static LocalDate termOnDate(int solarYear,int termIndex) {
		String info=SOLAR_SERM_INFO[solarYear-MIN_YEAR];
		int index=(int) Math.floor( (termIndex-1)/4.0);
		index*=5;
		
		String termInfo=String.valueOf( Integer.valueOf(info.substring(index, index+5),16));
		String dayStr;
		switch ((termIndex-1)%4) {
		case 1:
			dayStr= termInfo.substring(1, 3);
			break;
		case 2:
			dayStr= termInfo.substring(3, 4);
			break;
		case 3:
			dayStr= termInfo.substring(4, 6);
			break;
		default:
			dayStr=termInfo.substring(0, 1);
			break;
		}
		
		return LocalDate.of(solarYear, (int)Math.ceil(termIndex/2.0), Integer.parseInt(dayStr));
		
	}
	
	/**
	 * 将月份转换成通俗表示 [正,二……,冬,腊]
	 * @param month 农历月份
	 * @return
	 */
	private static String toChsMonth(int month) {
		return LUNAR_MONTH[month-1]+"月";
	}
	
	/**
	 * 将日期转换为通俗表示[初一，初十，廿一]
	 * @param day 农历天
	 * @return
	 */
	private static String toChsDay(int day) {
		return LUNAR_DATE[day-1];
	}
	
	/**
	 * 获取生肖
	 * @param year 农历年
	 * @return
	 */
	private static String zodiac(int year) {
		 return ZODIAC[(year-4)%12];
	}
}
