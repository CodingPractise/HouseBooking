package com.psg.guesthousebooking.controller;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import com.psg.guesthousebooking.model.Allotment;
import com.psg.guesthousebooking.service.ReportService;

@Path("/Report")
public class ReportController {
	ReportService reportGeneratorService;
	
	@Path("/occupancyreport")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Allotment generateOccupancyReport() throws JAXBException
	{
		Allotment allotment1 = new Allotment(new Date("24/02/2018"), new Date("25/02/2018"), 1, 1);
		return allotment1;
	}
}
