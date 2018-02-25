package com.psg.guesthousebooking.model;

import java.sql.Time;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.psg.guesthousebooking.utilities.DateUtilities;

/**
 * To handle the reservation request
 * @author Rajasri
 *
 */
@XmlRootElement
public class Reservation {

	
	private Date bookedFrom; //Expected check in date
	private Date bookedTo; //Expected check out date
	private Time fromTime;
	private Time toTime;
	private String guestName; //Guest Name
	private int guestId = 0; //Will be updated only during allotment by checking the mobile number and the guest name
 	private Date reservationDate; //Booking date or date on which reservation was requested
	private int reservationId; //Automatically generated reservation id
	private ReservationStatus status; //Reserved, cancelled, confirmed
	private String bookedBy;
	private String approvedBy;
	private int roomId;

	public Reservation()
	{
		
	}	
	
	public Reservation(Date bookedFrom, Date bookedTo,Time fromTime, Time toTime, String guestName,
			String bookedBy, String approvedBy) {
		super();
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.guestName = guestName;
		this.reservationDate = new Date(DateUtilities.getTodaysDate());
		this.bookedBy = bookedBy;
		this.approvedBy = approvedBy;
	}

	@XmlElement
	private int getReservationId() {
		return reservationId;
	}
	
	@XmlElement
	public Date getBookedFrom() {
		return bookedFrom;
	}

	@XmlElement
	public Date getBookedTo() {
		return bookedTo;
	}
	
	private String getGuestName() {
		return guestName;
	}

	@XmlTransient
	private Date getReservationDate() {
		return reservationDate;
	}

	@XmlElement
	private ReservationStatus getStatus() {
		return status;
	}

	@XmlTransient
	private String getBookedBy() {
		return bookedBy;
	}
	
	@XmlTransient
	private String getApprovedBy() {
		return approvedBy;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	private void setBookedFrom(Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	private void setBookedTo(Date bookedTo) {
		this.bookedTo = bookedTo;
	}

	private void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	private void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	private void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	private void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	private void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	private void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@XmlElement
	private int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	@XmlTransient
	private int getGuestId() {
		return guestId;
	}

	private Time getFromTime() {
		return fromTime;
	}

	private void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	private Time getToTime() {
		return toTime;
	}

	private void setToTime(Time toTime) {
		this.toTime = toTime;
	}
	
	
} 
