package com.psg.guesthousebooking.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.psg.guesthousebooking.utilities.DateUtilities;

/**
 * To handle the reservation request
 * @author Rajasri
 *
 */
public class Reservation {

	private Date bookedFrom; //Expected check in date
	private Date bookedTo; //Expected check out date
	private String guestName; //Guest Name
	private int guestId = 0; //Will be updated only during allotment by checking the mobile number and the guest name
 	private Date reservationDate; //Booking date or date on which reservation was requested
	private Time fromTime;
	private Time toTime;
	private int reservationId; //Automatically generated reservation id
	private ReservationStatus status; //Reserved, cancelled, confirmed
	private String bookedBy;
	private String approvedBy;

	public Reservation(Date bookedFrom, Date bookedTo, String guestName, Time fromTime, Time toTime,
			String bookedBy, String approvedBy) {
		super();
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.guestName = guestName;
		this.reservationDate = new Date(DateUtilities.getTodaysDate());
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.bookedBy = bookedBy;
		this.approvedBy = approvedBy;
	}

	public int getReservationId() {
		return reservationId;
	}
	
	public Date getBookedFrom() {
		return bookedFrom;
	}

	public Date getBookedTo() {
		return bookedTo;
	}

	public String getGuestName() {
		return guestName;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public void setBookedFrom(Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public void setBookedTo(Date bookedTo) {
		this.bookedTo = bookedTo;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
} 
