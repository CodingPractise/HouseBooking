package com.psg.guesthousebooking.dao;
import java.sql.Time;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.ReservationStatus;
import com.psg.guesthousebooking.model.Room;
import com.psg.guesthousebooking.model.RoomReservation;
import com.psg.guesthousebooking.model.RoomReservationStatus;
import com.psg.guesthousebooking.model.RoomType;

public class RoomDaoImplementation implements RoomDao {

	private static SessionFactory factory;
	
	@Override
	public boolean createRooms() {
		initializeSessionFactory();
		Session session = factory.openSession();
	    Transaction tx = null;
	    Integer roomId = null;
		      
	    try {
	        tx = session.beginTransaction();
	        
	        //Creating rooms for guestHouse         
	        
	        for (int i = 1; i <= 11; i++) {	
	        	RoomType type;
	        	if(i%2 == 0)
	        	{
	        		type = RoomType.AC;
	        	}
	        	else
	        	{
	        		type = RoomType.NON_AC;
	        	}
		        Room r = new Room(type, (i%2)+1);	        	        
		        roomId = (Integer) session.save(r);
		        System.out.println("Room Id: "  + roomId + " generated as " + type);
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

	
	
	private void initializeSessionFactory()
	{
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
}
