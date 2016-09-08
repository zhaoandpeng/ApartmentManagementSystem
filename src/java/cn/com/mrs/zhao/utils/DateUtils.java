package cn.com.mrs.zhao.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	
	public static Date stringToDate(String time) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		return date;
	}
	
	public static Date dateToDate(Date date) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);  
		date = DateUtils.stringToDate(dateString);
		return date;
	}
}
