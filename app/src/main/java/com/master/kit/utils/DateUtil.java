package com.master.kit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static SimpleDateFormat sDefaultDateFormat;
	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String sDefaultPattern = "yyyy-MM-dd HH:mm:ss";

	public static Calendar getCurrentCalendar() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	public static SimpleDateFormat getDefaultDateFormat(){
		if(sDefaultDateFormat == null){
			sDefaultDateFormat = new SimpleDateFormat(sDefaultPattern);
		}
		return sDefaultDateFormat;
	}

	public static String getCurrentTime(String pattern) throws ParseException {
		Date date = new Date();
		return formartDateToString(date, pattern);
	}

	public static String getCurrentTime() {
		Date date = new Date();
		try {
			return formartDateToString(date, sDefaultPattern);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date formartStringToDate(String time) throws ParseException {
		return getDefaultDateFormat().parse(time);
	}

	public static Date formartStringToDate(String time, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(time);
	}

	public static Calendar formartStringToCalendar(String time) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDefaultDateFormat().parse(time));
		return calendar;
	}
	/**
	 * 缺省部分会用0来补充
	 * @param time
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Calendar formartStringToCalendar(String time, String pattern) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		calendar.setTime(format.parse(time));
		return calendar;
	}

	public static String formartDateToString(Date date) throws ParseException {
		return getDefaultDateFormat().format(date);
	}

	public static String formartDateToString(Date date, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static String formartCalendarToString(Calendar calendar) throws ParseException {
		return getDefaultDateFormat().format(calendar.getTime());
	}

	public static String formartCalendarToString(Calendar calendar, String pattern) throws ParseException {
		return formartDateToString(calendar.getTime(), pattern);
	}

	public static String formart(String time, String srcPattern, String destPattern) throws ParseException {
		Date date = formartStringToDate(time, srcPattern);
		return formartDateToString(date, destPattern);
	}

	public static Calendar copyCalendar(Calendar calendar) {
		Calendar result = Calendar.getInstance();
		result.setTime(calendar.getTime());
		return result;
	}
	static int[] fields;

	/**
	 * 比较到指定单位field
	 * first 大于 second 返回正数
	 * first = second 返回0
	 * first 小于 second 返回负数
	 * @param first
	 * @param second
	 * @param field
     * @return
     */
	public static int compare(Calendar first,Calendar second,int field){

		for(int temp:getFiedls()){
			int result = first.get(temp)-second.get(temp);
			//如果结果 两个数不想等，或者只比较到这个单位那么已经得到比较结果
			if(result != 0||field ==temp){
				return result;
			}
		}
		return 0;
	}
	public static int[] getFiedls(){
		if(fields==null){
			fields = new int[]{Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND};
		}
		return fields;
	}

}
