package com.psg.guesthousebooking.service;

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
		if(fromDate.compareTo(toDate) > 0)
		{
			return null;
		}
		
		OccupancyReport report = new OccupancyReport();
		ReservationDao daoObject = new ReservationDaoImplementation();
		report.setReservations(daoObject.getReservations(fromDate, toDate));
		
		RoomDao rooms = new RoomDaoImplementation();
		long noOfRooms = rooms.getNoOfRooms();
		float occupiedRooms = 0;
		for (Reservation reservation : report.getReservations()) {
			float reservationDuration = DateUtilities.getNoOfDays(reservation.getBookedFrom(), reservation.getBookedTo()); 
			occupiedRooms += reservationDuration; //Since 1 reservation is associated with only 1 room
		}
		
		float duration = DateUtilities.getNoOfDays(fromDate, toDate)*noOfRooms;
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
}
