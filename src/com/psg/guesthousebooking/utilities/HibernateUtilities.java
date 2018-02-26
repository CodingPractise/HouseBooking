package com.psg.guesthousebooking.utilities;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {

        public static final SessionFactory tmrSessionFactory;

        static {
            try {

                tmrSessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (HibernateException ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            return tmrSessionFactory;
        }

        /* getters and setters here */

}
