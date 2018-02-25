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
	public int getReservationId() {
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
	
	public String getGuestName() {
		return guestName;
	}

	@XmlTransient
	public Date getReservationDate() {
		return reservationDate;
	}

	@XmlElement
	public ReservationStatus getStatus() {
		return status;
	}

	@XmlTransient
	public String getBookedBy() {
		return bookedBy;
	}
	
	@XmlTransient
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

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@XmlElement
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	@XmlTransient
	public int getGuestId() {
		return guestId;
	}

	public Time getFromTime() {
		return fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public Time getToTime() {
		return toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}
	
	
} 
