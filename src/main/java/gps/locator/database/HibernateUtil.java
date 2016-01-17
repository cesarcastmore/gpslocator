package gps.locator.database;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import gps.locator.model.CategoryMenu;
import gps.locator.model.Menu;
/*
 * 
 * 
 *  https://developer.jboss.org/wiki/SessionsAndTransactions
 * 
 */
import gps.locator.model.Service;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
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