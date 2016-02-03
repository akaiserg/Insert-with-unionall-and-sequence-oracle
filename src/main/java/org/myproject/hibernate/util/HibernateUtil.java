package org.myproject.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * Hibernate util to handle the  connection with the DB
 *
 */
public class HibernateUtil {

	/**
	 * Session factory  Hibernate
	 */
	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Builds  the session
	 * @return
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Returns  the session
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Closes  the session
	 */
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}