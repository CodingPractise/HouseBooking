package com.psg.guesthousebooking.controller;

import java.sql.Time;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.psg.guesthousebooking.model.RoomType;
import com.psg.guesthousebooking.service.ReservationService;
import com.psg.guesthousebooking.utilities.DateUtilities;

/**
 * To handle Room reservation and cancellation
 * @author Rajasri
 *
 */
@Path("/Reservation")
public class ReservationController {
	
	ReservationService reservationService;

	@PUT
	@Path("/Reserve")
	@Produces(MediaType.TEXT_PLAIN)
	public String reserveRoom(@QueryParam("frdt") String fromDate,
	        @QueryParam("todt") String toDate,
	        @QueryParam("frhr") int fromHour,
	        @QueryParam("frmin") int fromMin,
	        @QueryParam("frsec") int fromSec,
	        @QueryParam("tohr") int toHour,
	        @QueryParam("tomin") int toMin,
	        @QueryParam("tosec") int toSec,
	        @QueryParam("bkdby") String bookedBy,
	        @QueryParam("gname") String guestName,
	        @QueryParam("rtype") RoomType type,
	        @QueryParam("staff") String approver
	        )
	
	{
	
		reservationService  = new ReservationService();
		//If this has been a real time application, I prefer using new Time(Long) instead of this deprecated method
		return (reservationService.reserve(DateUtilities.getDateTime(fromDate),DateUtilities.getDateTime(toDate),new Time(fromHour, fromMin, fromSec),new Time(toHour, toMin, toSec), bookedBy, guestName, type, approver)) ? "SUCCESS" : "DENIED";
		
	}
	
	@POST
	@Path("/Cancel")	
	public boolean cancelRoom(@QueryParam("frdt") String fromDate,
	        @QueryParam("todt") String toDate,
	        @QueryParam("gname") String guestName)
	{
		reservationService = new ReservationService();
		return reservationService.cancelRoom(guestName, DateUtilities.getDateTime(fromDate), DateUtilities.getDateTime(toDate));
	}

}
