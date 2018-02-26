package com.psg.guesthousebooking.dao;

import java.util.List;

import com.psg.guesthousebooking.model.Guest;

/**
 * To maintain Guest details
 * @author Rajasri
 *
 */
public interface GuestDao {
	public boolean addGuest(Guest guest);
	public List<Guest> getAllGuests();
}
