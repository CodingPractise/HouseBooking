package com.psg.guesthousebooking.model;

import java.util.Date;

/**
 * Used to link Reservation, Room and actual check in date and checkout date
 * Collection of this class can be used directly to generate the occupancy report for a given date 
 * @author Rajasri
 *
 */
public class AllotmentDetail {
	private int occupancyId;
	private Date checkIn;
	private Date checkOut;
	private int reservationId;
	private int roomId;
	
	public AllotmentDetail(int occupancyId, Date checkIn, Date checkOut, int reservationId, int roomId) {
		super();
		this.occupancyId = occupancyId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservationId = reservationId;
		this.roomId = roomId;
	}
	
	
}
