package com.psg.guesthousebooking.dao;

import java.util.Date;
import java.util.List;

import com.psg.guesthousebooking.model.Allotment;

/**
 * To maintain allotment details
 * @author Rajasri
 *
 */
public interface AllotmentDao {
	public boolean addAllotment(Allotment allotment);
	public List<Integer> getOccupiedRooms(Date date);
	public boolean updateCheckOutDate(Date checkOutDate);
}
