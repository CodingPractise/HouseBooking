package com.psg.guesthousebooking.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="occupancy_report")
public class OccupancyReport {
	
	private OccupancyReport occupancy;
	
	private List<Reservation> reservations;
	
	private float occupancyPercentage;
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public void setOccupancyPercentage(float occupancyPercentage) {
		this.occupancyPercentage = occupancyPercentage;
	}
	
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	@XmlElement(name="occupancy_percentage")
	public double getOccupancyPercentage() {
		return occupancyPercentage;
	}
	
	@XmlTransient
	public OccupancyReport getInstance()
	{
		if(occupancy == null)
			occupancy = new OccupancyReport();
		return occupancy;
	}
}
