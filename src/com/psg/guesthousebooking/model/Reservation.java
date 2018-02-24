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
	
	private int reservationId; //Automatically generated reservation id
	private Date bookedFrom; //Expected check in date
	private Date bookedTo; //Expected check out date
	private ReservationStatus status; //Reserved, cancelled, confirmed
	private int guestId; //Guest id
	private Date reservationDate; //Booking date or date on which reservation was requested
	
	public Reservation(Date bookedFrom, Date bookedTo, int guestId) {
		super();
		this.reservationId = reservationCounter++;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.guestId = guestId;
		this.reservationDate = new Date(DateUtilities.getTodaysDate());
	}
	
} 
