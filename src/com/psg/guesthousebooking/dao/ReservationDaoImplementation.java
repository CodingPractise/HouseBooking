package com.psg.guesthousebooking.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.psg.guesthousebooking.model.Guest;
import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.ReservationStatus;
import com.psg.guesthousebooking.model.RoomReservation;
import com.psg.guesthousebooking.model.RoomReservationStatus;
import com.psg.guesthousebooking.model.RoomType;


public class ReservationDaoImplementation implements ReservationDao{
	
	private static SessionFactory factory;
	
	private boolean checkRoomAvailability(int noOfRooms, RoomType roomType, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String queryText = "SELECT COUNT(*) FROM t_room WHERE c_room_type='' AND c_room_id NOT IN "
				+ "(SELECT c_room_id FROM t_allotment)";
		//If a room is not available in t_allotment for a particular date, then the room is available
		
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
		String queryText = "SELECT t_room.c_room_id FROM t_room WHERE t_room.c_room_id NOT IN ("
								+"SELECT c_room_id FROM t_reserved_room INNER JOIN t_reservation ON t_reservation.c_reservation_id = t_reserved_room.c_reservation_id"
								+ "AND t_reservation.c_from_date <= '"+new String("from date")+"'  AND t_reservation.c_to_date >= '"+new String("to date")+"'"
								+ "AND t_reservation.c_from_time <= '"+new String("from time")+"'  AND t_reservation.c_to_time >= '"+new String("to time")+"'"
						 + ")";		
		
		List<Integer> rids = new ArrayList<>();
		rids.add(1);
		rids.add(5);
		return rids;
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
	public boolean addReservation(int noOfRooms, RoomType roomType, Date fromDate, Date toDate, Time fromTime,
			Time toTime, String bookedBy, String guestName, String approvedBy) {

		Reservation reservation = new Reservation(fromDate, toDate, guestName, fromTime, toTime, bookedBy, approvedBy);
		
		initializeSessionFactory();
		Session session = factory.openSession();
	    Transaction tx = null;
	    Integer reservationId = null;
		      
	    try {
	        tx = session.beginTransaction();
	        
	        //Updating Reservation data in DB
	        boolean isRoomAvailable = checkRoomAvailability(noOfRooms, roomType, fromDate, toDate);	         
	        ReservationStatus requestStatus = (isRoomAvailable) ? ReservationStatus.RESERVED : ReservationStatus.DENIED;
	        reservation.setStatus(requestStatus);
	        
	        reservationId = (Integer) session.save(reservation);

	        //Updating rooms booked for a reservation
	        if(isRoomAvailable)
	        {
		         for (int roomId : getAvailableRooms(noOfRooms, roomType)) {
		        	 RoomReservation roomsReserved = new RoomReservation(roomId, reservationId, RoomReservationStatus.RESERVED);	
		        	 session.save(roomsReserved);
				 }
	        }
        
	        tx.commit();
	        
	        return true;
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	      } finally {
	         session.close(); 
	      }
		
		return false;
	}

	@Override
	public boolean cancelReservation(String guestName, Date fromDate, Date toDate, int noOfRooms) {
		// TODO Auto-generated method stub
		return false;
	}


}
