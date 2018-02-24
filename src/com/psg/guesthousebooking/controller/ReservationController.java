package com.psg.guesthousebooking.controller;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.psg.guesthousebooking.service.ReservationService;

@Path("/Reservation")
public class ReservationController {
	
	ReservationService reservationService;

	@PUT
	@Path("/Reserve")
	public boolean reserveRoom()
	{
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
