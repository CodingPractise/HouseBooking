package com.psg.guesthousebooking.model;

public class Room {
	private int roomId;
	private RoomType roomtype;
	private int floorId;
	public Room(int roomId, RoomType roomtype, int floorId) {
		super();
		this.roomId = roomId;
		this.roomtype = roomtype;
		this.floorId = floorId;
	}
	
}
