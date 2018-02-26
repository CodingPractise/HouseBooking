package com.psg.guesthousebooking.service;

import com.psg.guesthousebooking.dao.RoomDaoImplementation;

public class RoomService {

	/**
	 * Test stub to create 10 rooms of random type
	 * @return
	 */
	public boolean createRooms()
	{
		RoomDaoImplementation implementation = new RoomDaoImplementation();
		return implementation.createRooms();
	}
}
