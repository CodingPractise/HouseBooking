package com.psg.guesthousebooking.controller;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.psg.guesthousebooking.service.RoomService;

@Path("/Rooms")
public class RoomController {
	RoomService roomService;
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createRooms()
	{
		roomService  = new RoomService();
		return roomService.createRooms();
	}
}
