package cn.com.shanda.aesop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateUtil {
	
	public static Date getDate(String dateTime) throws ParseException {
		
		DateFormat format = DateFormat.getDateTimeInstance();
		
		Date date = format.parse(dateTime);
		
		return date;
	}
}
