package com.psg.guesthousebooking.service;

import com.psg.guesthousebooking.dao.RoomDaoImplementation;

public class RoomService {

	public boolean createRooms()
	{
		RoomDaoImplementation implementation = new RoomDaoImplementation();
		return implementation.createRooms();
	}
}
