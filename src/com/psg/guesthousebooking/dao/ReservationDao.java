package com.psg.guesthousebooking.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.RoomType;

public interface ReservationDao {
	
	public List<Reservation> getReservations(Date bookFrom, Date bookTo);

	boolean cancelReservation(String guestName, Date fromDate, Date toDate);

	boolean addReservation(RoomType roomType, Date fromDate, Date toDate, Time fromTime, Time toTime, String bookedBy,
			String guestName, String approvedBy);
}
