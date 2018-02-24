package com.psg.guesthousebooking.model;

public class Room {
	private int floorId;
	private int roomId;
	private RoomType roomtype;
	public Room(int roomId, RoomType roomtype, int floorId) {
		super();
		this.roomId = roomId;
		this.roomtype = roomtype;
		this.floorId = floorId;
	}
	public int getFloorId() {
		return floorId;
	}
	public int getRoomId() {
		return roomId;
	}
	public RoomType getRoomtype() {
		return roomtype;
	}
	
}
