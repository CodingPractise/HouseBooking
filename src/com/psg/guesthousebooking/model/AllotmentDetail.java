package com.psg.guesthousebooking.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Used to link Reservation, Room and actual check in date and checkout date
 * Collection of this class can be used directly to generate the occupancy report for a given date 
 * @author Rajasri
 *
 */
@XmlRootElement
public class AllotmentDetail {
	
	private static int occupancyCounter = 1;
	
	private Date checkIn;
	private Date checkOut;
	private int occupancyId;
	private int reservationId;
	private int roomId;
	
		
	public AllotmentDetail() {
		super();
	}

	public AllotmentDetail(Date checkIn, Date checkOut, int reservationId, int roomId) {
		super();
		this.occupancyId = occupancyCounter++;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservationId = reservationId;
		this.roomId = roomId;
	}

	@XmlElement
	public Date getCheckIn() {
		return checkIn;
	}

	@XmlElement
	public Date getCheckOut() {
		return checkOut;
	}
	
	@XmlElement
	public int getOccupancyId() {
		return occupancyId;
	}

	@XmlElement
	public int getReservationId() {
		return reservationId;
	}

	@XmlElement
	public int getRoomId() {
		return roomId;
	}
	
}
