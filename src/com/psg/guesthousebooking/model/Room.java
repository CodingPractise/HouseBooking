package com.psg.guesthousebooking.model;

public class Room {
	private int floorNumber;
	private int roomId;
	private RoomType roomType;
	
	public Room(RoomType roomtype, int floorId) {
		super();
		this.roomType = roomtype;
		this.floorNumber = floorId;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public int getRoomId() {
		return roomId;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	public void setFloorNumber(int floorId) {
		this.floorNumber = floorId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}	
}
