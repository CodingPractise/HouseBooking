package com.psg.guesthousebooking.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.psg.guesthousebooking.model.OccupancyReport;
import com.psg.guesthousebooking.service.ReportService;
import com.psg.guesthousebooking.utilities.DateUtilities;

@Path("/Reports")
public class ReportController {

	@GET
	@Path("/OccupancyReport")
	@Produces(MediaType.APPLICATION_XML)
	public OccupancyReport getRoom()
	{
		return new ReportService().fetchOccupancy(DateUtilities.getDateTime("02/01/2018"), DateUtilities.getDateTime("02/25/2018"));
		
	}	
}
