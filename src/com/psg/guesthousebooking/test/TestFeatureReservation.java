package com.psg.guesthousebooking.test;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.psg.guesthousebooking.model.OccupancyReport;
import com.psg.guesthousebooking.model.Reservation;
import com.psg.guesthousebooking.model.ReservationStatus;
import com.psg.guesthousebooking.model.Room;
import com.psg.guesthousebooking.model.RoomType;
import com.psg.guesthousebooking.service.ReportService;
import com.psg.guesthousebooking.service.ReservationService;
import com.psg.guesthousebooking.utilities.DateUtilities;
import com.psg.guesthousebooking.utilities.HibernateUtilities;

public class TestFeatureReservation {

	@Test
	public void testRoomReservation() {
		
		assertTrue(setUpMockDb());
		
		ReservationService reservationService = new ReservationService();
		assertTrue(reservationService.reserve(DateUtilities.getDateTime("01/04/2018"), DateUtilities.getDateTime("01/06/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "Kate", RoomType.NON_AC ,"staff1"));
		assertFalse(reservationService.reserve(DateUtilities.getDateTime("01/04/2018"), DateUtilities.getDateTime("01/06/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "Kate", RoomType.NON_AC ,"staff1"));
		assertTrue(reservationService.reserve(DateUtilities.getDateTime("01/04/2018"), DateUtilities.getDateTime("01/06/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "William", RoomType.AC ,"staff2"));
		assertTrue(reservationService.cancelRoom("William",DateUtilities.getDateTime("01/04/2018"), DateUtilities.getDateTime("01/06/2018")));
		assertTrue(reservationService.reserve(DateUtilities.getDateTime("01/04/2018"), DateUtilities.getDateTime("01/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "Williams", RoomType.AC ,"staff1"));
		
		ReportService reportService = new ReportService();
		OccupancyReport actualReport = reportService.fetchOccupancy(DateUtilities.getDateTime("01/01/2018"), DateUtilities.getDateTime("01/10/2018"));
		
		OccupancyReport expectedOccupancy = new OccupancyReport();
		expectedOccupancy.setOccupancyPercentage(30.00f);
		assertEquals(expectedOccupancy.getOccupancyPercentage(), actualReport.getOccupancyPercentage(), 0);		
	}

	private boolean setUpMockDb()
	{
		boolean mockStatus = true;
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		Transaction mockDbTx = null;
		
		try {
			mockDbTx = session.beginTransaction();
			
			// creating rooms
			for (Room room : createRooms()) {
				session.save(room);
			}
			
			//creating reservations
			for (Reservation reservation : createReservations()) {
				session.save(reservation);
			}
	       
			mockDbTx.commit();
			
	      } catch (HibernateException e) {
	         if (mockDbTx!=null) mockDbTx.rollback();
	         mockStatus = false;
	      } finally {
	         session.close(); 
	      }
		
		return mockStatus;
	}
	
	/**
	 * Sets up Room table
	 * @return
	 */
	private List<Room> createRooms()
	{
		
		List<Room> rooms = new ArrayList<>();

		rooms.add(new Room(RoomType.NON_AC, 1));
		rooms.add(new Room(RoomType.NON_AC, 1));
		rooms.add(new Room(RoomType.AC, 2));
		rooms.add(new Room(RoomType.NON_AC, 2));
		rooms.add(new Room(RoomType.NON_AC, 2));
		
		return rooms;
	}
	
	
	
	/**
	 * Sets up Reservations table
	 * @return
	 */
	private List<Reservation> createReservations()
	{
		
		List<Reservation> reservations = new ArrayList<>();
		
		Reservation reservationOne = new Reservation(DateUtilities.getDateTime("01/01/2018"), DateUtilities.getDateTime("01/02/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Alice", "Abc and co", "staff1");
		reservationOne.setRoomId(1);
		reservationOne.setStatus(ReservationStatus.CHECKEDOUT);
		
		Reservation reservationTwo = new Reservation(DateUtilities.getDateTime("01/03/2018"), DateUtilities.getDateTime("01/05/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Bob", "Abc and co", "staff1");
		reservationTwo.setRoomId(2);
		reservationTwo.setStatus(ReservationStatus.CONFIRMED);
		
		Reservation reservationThree = new Reservation(DateUtilities.getDateTime("01/05/2018"), DateUtilities.getDateTime("01/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Charles", "Def pvt ltd", "staff1");
		reservationThree.setRoomId(4);
		reservationThree.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservationFour = new Reservation(DateUtilities.getDateTime("01/09/2018"), DateUtilities.getDateTime("01/10/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Dev", "Def pvt ltd", "staff2");
		reservationFour.setRoomId(1);
		reservationFour.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservationFive = new Reservation(DateUtilities.getDateTime("01/03/2018"), DateUtilities.getDateTime("01/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Ian", "Abc and co", "staff2");
		reservationFive.setRoomId(1);
		reservationFive.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservationSix = new Reservation(DateUtilities.getDateTime("01/03/2018"), DateUtilities.getDateTime("01/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "James", "Abc and co", "staff2");
		reservationSix.setRoomId(-1);
		reservationSix.setStatus(ReservationStatus.DENIED);
		
		
		reservations.add(reservationOne);
		reservations.add(reservationTwo);
		reservations.add(reservationThree);
		reservations.add(reservationFour);
		reservations.add(reservationFive);
		reservations.add(reservationSix);
		
		return reservations;
	}
}
