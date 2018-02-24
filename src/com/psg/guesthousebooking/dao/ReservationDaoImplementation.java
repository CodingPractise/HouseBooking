package com.psg.guesthousebooking.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.ReservationStatus;
import com.psg.guesthousebooking.model.RoomReservation;
import com.psg.guesthousebooking.model.RoomReservationStatus;
import com.psg.guesthousebooking.model.RoomType;


public class ReservationDaoImplementation implements ReservationDao{
	
	private static SessionFactory factory;
	
	@Override
	/**
	 * For allocating rooms for existing guests
	 */
	public boolean updateReservation(int reservationId, long mobileNo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	/**
	 * For allocating rooms for new guests
	 */
	public boolean updateReservation(int reservationId, long mobileNo, String address, String guestName) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkRoomAvailability(int noOfRooms, RoomType roomType, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		String queryText = "SELECT COUNT(*) FROM t_room WHERE c_room_type='' AND c_room_id NOT IN "
				+ "(SELECT c_room_id FROM t_allotment)";
		//If a room is not available in t_allotment for a particular date, then the room is available
		
		return false;
	}

	@Override
	public boolean addReservation(int noOfRooms, RoomType roomType, String fromDate, String toDate, String bookedBy) {
		if(checkRoomAvailability(noOfRooms, roomType, fromDate, toDate))
		{			
			Session session = factory.openSession();
		    Transaction tx = null;
		    Integer reservationId = null;
			      
		    try {
		         tx = session.beginTransaction();
		         
		         //Reservation request added successfully
		         Reservation reservation = new Reservation(new Date(fromDate), new Date(toDate), ReservationStatus.RESERVED, bookedBy);
		         reservationId = (Integer) session.save(reservation);
		         
		         //Rooms occupied for the reservation to be updated
		         for (int roomId : getAvailableRooms(noOfRooms, roomType)) {
		        	 RoomReservation roomsReserved = new RoomReservation(roomId, reservationId, RoomReservationStatus.RESERVED);	
		        	 session.save(roomsReserved);
				 }
		         
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		      } finally {
		         session.close(); 
		      }	
		}
		return false;
	}
	
	/**
	 * Identify "noOfRooms" for a given roomType
	 * @param noOfRooms : no of rooms to be identified
	 * @param roomType : room type
	 * @return : list of rooms that will be reserved
	 */
	private List<Integer> getAvailableRooms(int noOfRooms, RoomType roomType)
	{
		
		return Collections.EMPTY_LIST;
	}
	
	
	private void initializeSessionFactory()
	{
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         throw new ExceptionInInitializerError(ex); 
	      }
	}

	@Override
	public boolean cancelReservation(long mobileNumber, String fromDate, String toDate, int noOfRooms) {
		// TODO Auto-generated method stub
		return false;
	}


}
