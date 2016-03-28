package com.master.kit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static SimpleDateFormat sDefaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		return sDefaultDateFormat.parse(time);
	}

	public static Date formartStringToDate(String time, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(time);
	}

	public static Calendar formartStringToCalendar(String time) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sDefaultDateFormat.parse(time));
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
		return sDefaultDateFormat.format(date);
	}

	public static String formartDateToString(Date date, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static String formartCalendarToString(Calendar calendar) throws ParseException {
		return sDefaultDateFormat.format(calendar.getTime());
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


}
