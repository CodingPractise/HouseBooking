package com.psg.guesthousebooking.service;

import java.sql.Time;
import java.util.Date;

import com.psg.guesthousebooking.dao.ReservationDaoImplementation;
import com.psg.guesthousebooking.model.RoomType;

public class ReservationService {
	public boolean reserve(Date fromDate, Date toDate, Time fromTime, Time toTime,String bookedBy, String guestName, int noOfRooms, RoomType roomType, String approvedBy)
	{
		ReservationDaoImplementation daoObject = new ReservationDaoImplementation();
		return daoObject.addReservation(noOfRooms, roomType, fromDate, toDate, fromTime, toTime, bookedBy, guestName, approvedBy);
	}
	
}
