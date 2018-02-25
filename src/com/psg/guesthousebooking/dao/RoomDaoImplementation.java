package com.psg.guesthousebooking.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.psg.guesthousebooking.model.Room;
import com.psg.guesthousebooking.model.RoomType;
import com.psg.guesthousebooking.utilities.HibernateUtilities;

public class RoomDaoImplementation implements RoomDao {

	@Override
	public boolean createRooms() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			for (int i = 1; i <= 11; i++) {
				RoomType type;
				type = (i % 2 == 0) ? RoomType.AC : RoomType.NON_AC;
				Room r = new Room(type, (i % 2) + 1);
				session.save(r);
			}

			tx.commit();

			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	@Override
	public long getNoOfRooms() {
		return (long) HibernateUtilities.getSessionFactory().openSession().createCriteria(Room.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}
}
