package com.psg.guesthousebooking.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {
	
	/**
	 * To generate today's date
	 * @return
	 */
	public static String getTodaysDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();		
		return dateFormat.format(date);
	}
}
