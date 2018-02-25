package com.psg.guesthousebooking.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.ReservationStatus;
import com.psg.guesthousebooking.model.RoomType;
import com.psg.guesthousebooking.utilities.HibernateUtilities;


public class ReservationDaoImplementation implements ReservationDao{

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Reservation> getReservations(Date bookFrom, Date bookTo)
	{
		Session session = HibernateUtilities.getSessionFactory().openSession();
	    
	    Criteria reservation = session.createCriteria(Reservation.class);
	    reservation.add(Restrictions.between("bookedFrom", bookFrom, bookTo));
	    reservation.add(Restrictions.between("bookedTo", bookFrom, bookTo));
	    reservation.add(Restrictions.ne("status", ReservationStatus.DENIED));
	    reservation.add(Restrictions.ne("status", ReservationStatus.CANCELLED));
    
	    return reservation.list();

	}

	@Override
	public boolean addReservation(RoomType roomType, Date fromDate,Date toDate,Time fromTime, Time toTime, String bookedBy, String guestName, String approvedBy) {

		Reservation reservation = new Reservation(fromDate, toDate, fromTime, toTime, guestName, bookedBy, approvedBy);

		boolean reservationStatus = false;
		Session session = HibernateUtilities.getSessionFactory().openSession();
	    Transaction tx = null;
		      
	    try {
	        tx = session.beginTransaction();
	        
	        //Updating Reservation data in DB
	        int roomId = getRoom(roomType, fromDate, toDate);
	        ReservationStatus requestStatus = (roomId > -1 ) ? ReservationStatus.RESERVED : ReservationStatus.DENIED;
	        reservation.setStatus(requestStatus);
	        reservation.setRoomId(roomId);
	        session.save(reservation);
	        tx.commit();
	        if(roomId > -1)
	        {
	        	reservationStatus = true;
	        }
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();	         
	      } finally {
	         session.close(); 
	      }
	    return reservationStatus;
	}

	@Override
	public boolean cancelReservation(String guestName, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		List<Reservation> reservation = getReservation(guestName, fromDate, toDate);
		if(null != reservation && reservation.size() == 1 )
		{
			Session session = HibernateUtilities.getSessionFactory().openSession();
			Transaction tx = null;
			try {
		        tx = session.beginTransaction();
		        
		        //Updating Reservation data in DB
		        Reservation reservationRequest = reservation.get(0);
		        reservationRequest.setStatus(ReservationStatus.CANCELLED);
		        session.update(reservationRequest);	        
		        tx.commit();
	        
		        return true;
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		      } finally {
		         session.close(); 
		      }
			
			
		}
		return false;
	}
	
	private List<Reservation> getReservation(String guestName, Date fromDate, Date toDate)
	{
		Session session = HibernateUtilities.getSessionFactory().openSession();
	    
	    Criteria reservation = session.createCriteria(Reservation.class);
	    reservation.add(Restrictions.between("bookedFrom", fromDate, fromDate));
	    reservation.add(Restrictions.between("bookedTo", toDate, toDate));
	    reservation.add(Restrictions.eq("status", ReservationStatus.RESERVED));
	    reservation.add(Restrictions.eq("guestName", guestName));
   
	    return reservation.list();
	}

	private int getRoom(RoomType roomType, Date fromDate,Date toDate)
	{
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Query query = session.createQuery("select r.roomId from Room as r where r.roomType = :rType "
							+ "and r.roomId not in "	
							+ "("
								+ "select re.roomId from Reservation as re where "
								+ "("
									+ "("
										+ "(re.bookedTo >= :fromDate and re.bookedTo <= :toDate)"
												+ "or "
										+ "(re.bookedFrom >= :fromDate and re.bookedFrom <= :toDate)"
												+ "or "
										+ "(re.bookedFrom <= :fromDate and re.bookedTo >= :toDate)"
										+ ")"
									+ "and "
									+ "(re.status = :confirmed or re.status = :reserved )"
								+ ")"
							+ ")");
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameter("rType", roomType);
		query.setParameter("confirmed", ReservationStatus.CONFIRMED);
		query.setParameter("reserved", ReservationStatus.RESERVED);
		
		query.setMaxResults(1);
		
		List<Integer> result = query.list();
		if(result.size() >= 1)
		{
			for (Integer integer : result) {
				return integer;
			}
		}		
		return -1;
	}

}
