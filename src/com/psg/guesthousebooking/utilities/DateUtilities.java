package com.psg.guesthousebooking.utilities;

import java.sql.Time;
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
			return null;
		}
	}
	
	public static double getNoOfDays(Date startDate,Time fromTime, Date endDate, Time endTime)
	{
		//If this has been a real time application, I prefer using new Date(long) instead of this deprecated method
		Date from = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate(), fromTime.getHours(), fromTime.getMinutes(), fromTime.getSeconds());
		Date to = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), endTime.getHours(), endTime.getMinutes(), endTime.getSeconds());

		long diff = to.getTime() - from.getTime();		 
		
		return diff / (24 * 60 * 60 * 1000.00);

	}
	
	public static boolean isCurrentOrFutureDate(Date startDate,Time fromTime, Date endDate)
	{
		//If this has been a real time application, I prefer using new Date(long) instead of this deprecated method
		Date from = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate(), fromTime.getHours(), fromTime.getMinutes(), fromTime.getSeconds());
		return ((from.getTime() - endDate.getTime()) >= 0);

	}
	
		
}
