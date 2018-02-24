package com.psg.guesthousebooking.model;

import java.util.Date;

/**
 * To handle the reservation request
 * @author Rajasri
 *
 */
public class Reservation {
	private Date bookedFrom; //Expected check in date
	private Date bookedTo; //Expected check out date
	private int guestId; //Guest id
	private Date reservationDate; //Booking date or date on which reservation was requested
	private int reservationId; //Automatically generated reservation id
	private int[] roomsReserved; //One booking can involve many rooms
	private ReservationStatus status; //Reserved, cancelled, confirmed
	
	public Reservation(int reservationId, Date bookedFrom, Date bookedTo, ReservationStatus status, int guestId,
			Date reservationDate, int[] roomsReserved) {
		super();
		this.reservationId = reservationId;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.status = status;
		this.guestId = guestId;
		this.reservationDate = reservationDate;
		this.roomsReserved = roomsReserved;
	}

	
} 
