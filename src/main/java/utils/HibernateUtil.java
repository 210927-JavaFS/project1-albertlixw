package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	private static Session session;
	
	//only create singleton session for single thread app. 
	
	public static Session getSession() {
		if(session == null) {
			session = sessionFactory.openSession();
		}
		System.out.println(session.isConnected());
		System.out.println(session.isDirty());
		System.out.println(session.isOpen());
		System.out.println(session.getStatistics());

		return session;
	}
	
	public static void closeSession() {
		if(session!= null) {
//			session.flush();
			session.close();
		}
		session = null;
	}
	
	
}
