package com.psg.guesthousebooking.model;

import java.io.Serializable;

/**
 * Separate class is added since:
 * 1. There can be multiple room reservations for a single reservation
 * 2. Reservation can be updated by cancelling one or more rooms
 * @author Rajasri
 *
 */
public class RoomReservation implements Serializable{
	private int reservationId;
	private int roomId;
	private RoomReservationStatus status;
	
	public RoomReservation(int roomId, int reservationId, RoomReservationStatus status) {
		super();
		this.roomId = roomId;
		this.reservationId = reservationId;
		this.status = status;
	}
	
	public int getReservationId() {
		return reservationId;
	}
	public int getRoomId() {
		return roomId;
	}
	public RoomReservationStatus getStatus() {
		return status;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public void setStatus(RoomReservationStatus status) {
		this.status = status;
	}
	
}
