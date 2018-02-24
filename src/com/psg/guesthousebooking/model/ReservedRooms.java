package com.psg.guesthousebooking.model;
/**
 * Separate class is added since:
 * 1. There can be multiple room reservations for a single reservation
 * 2. Reservation can be updated by cancelling one or more rooms
 * @author Rajasri
 *
 */
public class ReservedRooms {
	private int roomId;
	private int reservationId;
	private ReservationStatus status;
	public ReservedRooms(int roomId, int reservationId, ReservationStatus status) {
		super();
		this.roomId = roomId;
		this.reservationId = reservationId;
		this.status = status;
	}
	
}
