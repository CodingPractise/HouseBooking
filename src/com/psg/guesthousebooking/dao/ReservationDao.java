package com.psg.guesthousebooking.dao;

import com.psg.guesthousebooking.model.RoomType;

public interface ReservationDao {
	boolean addReservation(int noOfRooms, RoomType roomType, String fromDate, String toDate,String bookedBy);
	boolean updateReservation(int reservationId, long mobileNo);
	boolean updateReservation(int reservationId, long mobileNo, String address, String guestName);
	boolean cancelReservation(long mobileNumber, String fromDate, String toDate, int noOfRooms);
}
