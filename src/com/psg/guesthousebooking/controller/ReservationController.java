package com.psg.guesthousebooking.controller;

import java.sql.Time;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.psg.guesthousebooking.model.RoomType;
import com.psg.guesthousebooking.service.ReservationService;

@Path("/Reservation")
public class ReservationController {
	
	ReservationService reservationService;

	@GET
	@Path("/Reserve")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean reserveRoom()
	{
		
		reservationService  = new ReservationService();
		reservationService.reserve(new Date("02/25/2018"), new Date("02/26/2018"), new Time(13, 10, 00), new Time(13, 10, 00), "Rajesh", "Khanna", 1, RoomType.NON_AC, "Jenifer");
		return true;
	}
	
	@POST
	@Path("/Allocate")
	public boolean allocateRoom()
	{
		return true;
	}

	
	@POST
	@Path("/Cancel")	
	public boolean cancelRoom(int reservationId)
	{
		return false;
	}
	
}
