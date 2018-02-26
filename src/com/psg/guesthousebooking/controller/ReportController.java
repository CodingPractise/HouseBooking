package com.psg.guesthousebooking.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.psg.guesthousebooking.model.OccupancyReport;
import com.psg.guesthousebooking.service.ReportService;
import com.psg.guesthousebooking.utilities.DateUtilities;

@Path("/Reports")
public class ReportController {

	@GET
	@Path("/OccupancyReport")
	@Produces(MediaType.APPLICATION_XML)
	public OccupancyReport getRoom(@QueryParam("from_date") String fromDate,
	        @QueryParam("to_date") String toDate)
	{
		return new ReportService().fetchOccupancy(DateUtilities.getDateTime(fromDate), DateUtilities.getDateTime(toDate));
		
	}	
}
