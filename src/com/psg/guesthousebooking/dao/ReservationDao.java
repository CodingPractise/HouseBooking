package com.psg.guesthousebooking.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.RoomType;

/**
 * To maintaing reservation details
 * @author Rajasri
 *
 */
public interface ReservationDao {
	
	/**
	 * To get all the reservations(checkedout, confirmed, reserved) available within a given date
	 * @param bookFrom : From date
	 * @param bookTo : To date
	 * @return List of reservations (non cancelled and non denied)
	 */
	public List<Reservation> getReservations(Date bookFrom, Date bookTo);

	/**
	 * To cancel a reservation request
	 * @param guestName : Name of the guest
	 * @param fromDate : Reservation from date
	 * @param toDate : Reservation to date
	 * @return : true if the cancellation is successful, false if not
	 */
	boolean cancelReservation(String guestName, Date fromDate, Date toDate);

	/**
	 * To create a reservation
	 * @param roomType : AC or NON_AC
	 * @param fromDate : from date
	 * @param toDate : to date
	 * @param fromTime : expected check in time
	 * @param toTime : expected check out time
	 * @param bookedBy : person who is raising the reservation request
	 * @param guestName : Name of the guest who will stay
	 * @param approvedBy : Hotel operator who is serving the request
	 * @return : true if reservation is successful, false if not
	 */
	boolean addReservation(RoomType roomType, Date fromDate, Date toDate, Time fromTime, Time toTime, String bookedBy,
			String guestName, String approvedBy);
}
