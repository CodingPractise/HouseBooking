package com.psg.guesthousebooking.model;

import java.util.Date;

import com.psg.guesthousebooking.utilities.DateUtilities;

/**
 * To handle the reservation request
 * @author Rajasri
 *
 */
public class Reservation {
	
	private static int reservationCounter = 1; 
	
	private Date bookedFrom; //Expected check in date
	private Date bookedTo; //Expected check out date
	private int guestId; //Guest id
	private Date reservationDate; //Booking date or date on which reservation was requested
	private int reservationId; //Automatically generated reservation id
	private ReservationStatus status; //Reserved, cancelled, confirmed
	private String bookedBy;
	
	@SuppressWarnings("deprecation")
	public Reservation(Date bookedFrom, Date bookedTo, ReservationStatus status, String bookedBy) {
		super();
		this.reservationId = reservationCounter++;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.status = status;
		this.reservationDate = new Date(DateUtilities.getTodaysDate());
		this.bookedBy = bookedBy;
	}

	public static int getReservationCounter() {
		return reservationCounter;
	}

	public Date getBookedFrom() {
		return bookedFrom;
	}

	public Date getBookedTo() {
		return bookedTo;
	}

	public int getGuestId() {
		return guestId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public int getReservationId() {
		return reservationId;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public String getBookedBy() {
		return bookedBy;
	}

} 
