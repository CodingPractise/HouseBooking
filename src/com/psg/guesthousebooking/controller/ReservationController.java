package com.psg.guesthousebooking.controller;

import java.sql.Time;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.psg.guesthousebooking.model.RoomType;
import com.psg.guesthousebooking.service.ReservationService;
import com.psg.guesthousebooking.utilities.DateUtilities;

@Path("/Reservation")
public class ReservationController {
	
	ReservationService reservationService;

	@GET
	@Path("/Reserve")
	@Produces(MediaType.TEXT_PLAIN)
	public String reserveRoom()
	{
	
		reservationService  = new ReservationService();
		return (reservationService.reserve(DateUtilities.getDateTime("03/02/2018"),DateUtilities.getDateTime("03/03/2018"),new Time(10, 30, 00),new Time(10, 30, 00), "Luella", "Rajasri", RoomType.NON_AC, "operator1")) ? "SUCCESS" : "DENIED";
		
	}
	
	@POST
	@Path("/Cancel")	
	public boolean cancelRoom()
	{
		reservationService = new ReservationService();
		return reservationService.cancelRoom("Werner", DateUtilities.getDateTime("02/12/2018"), DateUtilities.getDateTime("02/20/2018"));
	}

}
