package com.psg.guesthousebooking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	/**
	 * Reservation 7 (4 Mar to 6 Mar , NON_AC -> should get room 5)
	 * Reservation 8 (4 Mar to 6 Mar , NON_AC -> no room available. should be denied)
	 * Reservation 9 (4 Mar to 6 Mar , AC -> should get room 3)
	 * Reservation 10 (Cancellation request: 4 Mar to 6 Mar , AC (has room 3)) -> to be cancelled -> 3 should be available for next reservation (if any)
	 * Reservation 11 (4 Mar to 6 Mar, AC -> should get room 3)
	 * Occupancy report : total (Reservation and its duration : 1 - 1, 2 - 2, 3 - 2, 4 - 1, 5 - 4, 6 - 0, 7 - 2, 8 - 0, 9 - 0, 10 - 3) 
	 */
	public void testRoomReservation() {
		
		assertTrue(setUpMockDb());
		
		ReservationService reservationService = new ReservationService();
		assertTrue(reservationService.reserve(DateUtilities.getDateTime("03/04/2018"), DateUtilities.getDateTime("03/06/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "Kate", RoomType.NON_AC ,"staff1"));
		assertFalse(reservationService.reserve(DateUtilities.getDateTime("03/04/2018"), DateUtilities.getDateTime("03/06/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "Kate", RoomType.NON_AC ,"staff1"));
		assertTrue(reservationService.reserve(DateUtilities.getDateTime("03/04/2018"), DateUtilities.getDateTime("03/06/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "William", RoomType.AC ,"staff2"));
		assertTrue(reservationService.cancelRoom("William",DateUtilities.getDateTime("03/04/2018"), DateUtilities.getDateTime("03/06/2018")));
		assertTrue(reservationService.reserve(DateUtilities.getDateTime("03/04/2018"), DateUtilities.getDateTime("03/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "XYZ", "Williams", RoomType.AC ,"staff1"));
		
		ReportService reportService = new ReportService();
		OccupancyReport actualReport = reportService.fetchOccupancy(DateUtilities.getDateTime("03/01/2018"), DateUtilities.getDateTime("03/10/2018"));
		
		OccupancyReport expectedOccupancy = new OccupancyReport();
		expectedOccupancy.setOccupancyPercentage(30.00f);
		assertEquals(expectedOccupancy.getOccupancyPercentage(), actualReport.getOccupancyPercentage());		
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
	 * room 1, 2, 4, 5 -> Non ac
	 * room 3 -> AC
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
	 * considering a span of 1 Mar 2018 to 10 Mar 2018
	 * 5 rooms : 1 , 2 , 3 , 4, 5  (3 is AC, others NON_AC)
	 * 6 reservations:
	 * Room 1 occupied from 1 to 2, 3 to 7, 9 to 10
	 * Room 2 occupied from 3 to 5
	 * Room 4 occupied from 5 to 7
	 * Reservation 1 -> 1
	 * Reservation 2 -> 2
	 * Reservation 3 -> 4
	 * Reservation 4 -> 1
	 * Reservation 5 -> 1
	 * Reservation 6 -> denied
	 * @return
	 */
	private List<Reservation> createReservations()
	{
		
		List<Reservation> reservations = new ArrayList<>();
		
		Reservation reservationOne = new Reservation(DateUtilities.getDateTime("03/01/2018"), DateUtilities.getDateTime("03/02/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Alice", "Abc and co", "staff1");
		reservationOne.setRoomId(1);
		reservationOne.setStatus(ReservationStatus.CHECKEDOUT);
		
		Reservation reservationTwo = new Reservation(DateUtilities.getDateTime("03/03/2018"), DateUtilities.getDateTime("03/05/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Bob", "Abc and co", "staff1");
		reservationTwo.setRoomId(2);
		reservationTwo.setStatus(ReservationStatus.CONFIRMED);
		
		Reservation reservationThree = new Reservation(DateUtilities.getDateTime("03/05/2018"), DateUtilities.getDateTime("03/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Charles", "Def pvt ltd", "staff1");
		reservationThree.setRoomId(4);
		reservationThree.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservationFour = new Reservation(DateUtilities.getDateTime("03/09/2018"), DateUtilities.getDateTime("03/10/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Dev", "Def pvt ltd", "staff2");
		reservationFour.setRoomId(1);
		reservationFour.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservationFive = new Reservation(DateUtilities.getDateTime("03/03/2018"), DateUtilities.getDateTime("03/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "Ian", "Abc and co", "staff2");
		reservationFive.setRoomId(1);
		reservationFive.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservationSix = new Reservation(DateUtilities.getDateTime("03/03/2018"), DateUtilities.getDateTime("03/07/2018"), new Time(9, 00, 00), new Time(9, 00, 00), "James", "Abc and co", "staff2");
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
