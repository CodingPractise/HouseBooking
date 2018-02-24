package com.psg.guesthousebooking.dao;

import java.util.List;

import com.psg.guesthousebooking.model.Room;

public interface RoomDao {
	public boolean addRoom(Room room);
	public boolean addRooms(List<Room> rooms);
	
}
