package com.psg.guesthousebooking.dao;

public interface RoomDao {
	/**
	 * Test stub to create rooms. Available for code review/evaluation purpose
	 * @return : true if the rooms are created, false otherwise
	 */
	public boolean createRooms();
	
	/**
	 * To get the number of rooms available in the guesthouse
	 * @return : no of rooms available in the property
	 */
	public long getNoOfRooms();

}
