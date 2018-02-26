package com.psg.guesthousebooking.service;

import java.sql.Time;
import java.util.Date;

import com.psg.guesthousebooking.dao.ReservationDao;
import com.psg.guesthousebooking.dao.ReservationDaoImplementation;
import com.psg.guesthousebooking.dao.RoomDao;
import com.psg.guesthousebooking.dao.RoomDaoImplementation;
import com.psg.guesthousebooking.model.OccupancyReport;
import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.utilities.DateUtilities;

public class ReportService {
	
	public OccupancyReport fetchOccupancy(Date fromDate, Date toDate)
	{
		OccupancyReport report = new OccupancyReport();		
		
		if(!areParametersValid(fromDate, toDate))
		{
			report.setOccupancyPercentage(0);
			return new OccupancyReport();
		}

		ReservationDao daoObject = new ReservationDaoImplementation();
		report.setReservations(daoObject.getReservations(fromDate, toDate));
		
		RoomDao rooms = new RoomDaoImplementation();
		long noOfRooms = rooms.getNoOfRooms();
		report.setTotalNoOfRooms(noOfRooms);
		float occupiedRooms = 0;
		for (Reservation reservation : report.getReservations()) {
			
			double reservationDuration = Math.ceil(DateUtilities.getNoOfDays(reservation.getBookedFrom(), reservation.getFromTime() , reservation.getBookedTo(), reservation.getToTime())); 
			System.out.println("Reservation dur : " + reservation.getReservationId() + " " + reservationDuration);
			occupiedRooms += reservationDuration; //Since 1 reservation is associated with only 1 room
		}
		
		double noOfDays = DateUtilities.getNoOfDays(fromDate, new Time(00, 00, 00) , toDate, new Time(23, 59, 59));
		report.setNoOfDays(Math.ceil(noOfDays));
		//For a real application, I would prefer using new Time(long) instead of this deprecated method
		double duration = noOfDays * noOfRooms;

		try
		{
			report.setOccupancyPercentage(((occupiedRooms/duration)*100));
		}
		catch(NumberFormatException e)
		{
			report.setOccupancyPercentage(0);
		}
		
		return report;
		
	}
	
	private boolean areParametersValid(Date fromDate, Date toDate)
	{
		return (null != fromDate && null != toDate && fromDate.compareTo(toDate) <= 0);
	}
}
