package com.psg.guesthousebooking.dao;

import java.sql.Time;
import java.util.Date;

import com.psg.guesthousebooking.model.RoomType;

public interface ReservationDao {
	boolean cancelReservation(String guestName, Date fromDate, Date toDate, int noOfRooms);

	boolean addReservation(int noOfRooms, RoomType roomType, Date fromDate, Date toDate, Time fromTime, Time toTime,
			String bookedBy, String guestName, String approvedBy);
}
