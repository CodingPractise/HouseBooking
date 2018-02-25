package com.psg.guesthousebooking.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {
	
	/**
	 * To generate today's date
	 * @return
	 */
	public static String getTodaysDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();		
		return dateFormat.format(date);
	}
	
	public static Date getDateTime(String date)
	{
		try {
			
			return new SimpleDateFormat("MM/dd/yyyy").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public static float getNoOfDays(Date startDate, Date endDate)
	{
		long diff = startDate.getTime() - endDate.getTime();

		long diffDays = diff / (24 * 60 * 60 * 1000);
		
	    return diffDays;
	}
		
}
