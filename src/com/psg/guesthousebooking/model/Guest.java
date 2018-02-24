package com.psg.guesthousebooking.model;

/**
 * Used to represent the guests of the guest house
 * @author Rajasri
 *
 */
public class Guest {
	private int guestId;
	private String firstName;
	private String lastName;
	private String addressDetails;
	private long mobileNo;
	
	public Guest(int guestId, String firstName, String lastName, String addressDetails, long mobileNo) {
		super();
		this.guestId = guestId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressDetails = addressDetails;
		this.mobileNo = mobileNo;
	}
	
	
}
