package com.psg.guesthousebooking.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="occupancy_report")
public class OccupancyReport {
	
	private List<Reservation> reservations;
	
	private double occupancyPercentage;
	private long totalNoOfRooms;
	private double noOfDays;
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public void setOccupancyPercentage(double occupancyPercentage) {
		this.occupancyPercentage = occupancyPercentage;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	@XmlElement(name="occupancy_percentage")
	public String getOccupancyPercentage() {		
		return String.format("%.2f", occupancyPercentage);
	}

	public double getOccupancyPercentageAsNumber() {		
		return occupancyPercentage;
	}

	public long getTotalNoOfRooms() {
		return totalNoOfRooms;
	}
	public void setTotalNoOfRooms(long totalNoOfRooms) {
		this.totalNoOfRooms = totalNoOfRooms;
	}
	public double getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(double noOfDays) {
		
		this.noOfDays = noOfDays;
	}

	
}
