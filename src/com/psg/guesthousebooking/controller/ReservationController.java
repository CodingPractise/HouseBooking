package com.psg.guesthousebooking.controller;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import com.psg.guesthousebooking.model.AllotmentDetail;

@Path("/Reservation")
public class ReservationController {
	
	@POST
	public boolean allocateRoom()
	{
		return true;
	}

	
	@POST
	public boolean cancelRoom(int reservationId)
	{
		return false;
	}
	
	@Path("generateoccupancyreport")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public AllotmentDetail generateOccupancyReport() throws JAXBException
	{
		AllotmentDetail allotment1 = new AllotmentDetail(new Date("24/02/2018"), new Date("25/02/2018"), 1, 1);
		return allotment1;
	}
	
	@PUT
	public boolean reserveRoom()
	{
		return true;
	}

}
