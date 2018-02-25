package com.psg.guesthousebooking.model;

import java.util.Date;
import java.util.List;

/**
 * Used to link Reservation, Room and actual check in date and checkout date
 * Collection of this class can be used directly to generate the occupancy report for a given date 
 * @author Rajasri
 *
 */
public class Allotment {
	
	private int allotmentId;
	private Date checkIn;
	private Date checkOut;
	private Date checkInTime;
	private Date checkOutTime;
	private int reservationId;
	private List<RoomService> services;
		
	public Allotment() {
		super();
	}

	public Allotment(Date checkIn, Date checkOut, Date checkInTime, Date checkOutTime, int reservationId, List<RoomService> services) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.reservationId = reservationId;
		this.services = services;
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
	
	public List<RoomService> getServices()
	{
		return services;
	}
	
}
