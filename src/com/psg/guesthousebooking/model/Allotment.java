package com.psg.guesthousebooking.model;

import java.util.Date;

/**
 * Used to link Reservation, Room and actual check in date and checkout date
 * Collection of this class can be used directly to generate the occupancy report for a given date 
 * @author Rajasri
 *
 */
public class Allotment {
	
	private static int occupancyCounter = 1;
	
	private Date checkIn;
	private Date checkOut;
	private Date checkInTime;
	private Date checkOutTime;
	private int allotmentId;
	private int reservationId;
		
	public Allotment() {
		super();
	}

	public Allotment(Date checkIn, Date checkOut, Date checkInTime, Date checkOutTime, int reservationId) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.allotmentId = occupancyCounter++;
		this.reservationId = reservationId;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public int getOccupancyId() {
		return allotmentId;
	}

	public int getReservationId() {
		return reservationId;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public int getAllotmentId() {
		return allotmentId;
	}
	
	
}
