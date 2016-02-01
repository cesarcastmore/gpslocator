package gps.locator.database;


import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/*
 * 
 * 
 *  https://developer.jboss.org/wiki/SessionsAndTransactions
 * 
 */

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {

			sessionFactory = new Configuration().configure(new File("/home/ccastillo/workspace/gps-locator-api/hibernate.cfg.xml")).buildSessionFactory();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
}