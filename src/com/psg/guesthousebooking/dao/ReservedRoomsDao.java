package com.psg.guesthousebooking.dao;

import java.util.List;

import com.psg.guesthousebooking.model.RoomReservation;

public interface ReservedRoomsDao {
	
	public boolean addReservedRooms(List<RoomReservation> reservedRooms);
	public List<RoomReservation> getReservedRooms(int reservationId);
	
}
