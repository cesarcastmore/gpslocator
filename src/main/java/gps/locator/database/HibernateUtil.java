package gps.locator.database;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
			// Create the SessionFactory from hibernate.cfg.xml
			System.out.println("entro 1");

			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			System.out.println("entro 2");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
					applySettings(configuration.getProperties());
			System.out.println("entro 3");

			sessionFactory = configuration.buildSessionFactory(builder.build());

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