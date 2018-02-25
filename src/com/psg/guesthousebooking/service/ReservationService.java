package com.psg.guesthousebooking.service;

import java.sql.Time;
import java.util.Date;

import com.psg.guesthousebooking.dao.ReservationDaoImplementation;
import com.psg.guesthousebooking.model.RoomType;

public class ReservationService {
	public boolean reserve(Date fromDate, Date toDate,Time fromTime, Time toTime, String bookedBy, String guestName, RoomType roomType, String approvedBy)
	{
		ReservationDaoImplementation daoObject = new ReservationDaoImplementation();
		return daoObject.addReservation(roomType, fromDate, toDate, fromTime, toTime, bookedBy, guestName, approvedBy);
	}
	
	public boolean cancelRoom(String guestName, Date fromDate, Date toDate)
	{
		ReservationDaoImplementation dao = new ReservationDaoImplementation();
		return dao.cancelReservation(guestName, fromDate, toDate);
	}

}
