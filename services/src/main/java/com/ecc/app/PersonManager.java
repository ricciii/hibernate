package com.ecc.app;

import java.util.*;
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class PersonManager {
	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public PersonManager() {

	}

	public PersonManager(SessionFactory factory) {
		this.factory = factory;
	}

   	public Integer addPerson(Person person) {

		session = factory.openSession();
		Integer id = null;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(person); 
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction!=null) {
				transaction.rollback();
			} 
			e.printStackTrace();
		} finally {
			session.close(); 
		}
    return id;
   }
}