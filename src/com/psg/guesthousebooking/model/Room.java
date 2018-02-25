package com.psg.guesthousebooking.model;

public class Room {
	private int floorId;
	private int roomId;
	private RoomType roomType;
	
	public Room(RoomType roomtype, int floorId) {
		super();
		this.roomType = roomtype;
		this.floorId = floorId;
	}
	public int getFloorId() {
		return floorId;
	}
	public int getRoomId() {
		return roomId;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	
}
