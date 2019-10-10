package com.ecc.app;
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.hibernate.Criteria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;

public class GeneralService {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public GeneralService() {

	}

	public GeneralService(SessionFactory factory) {
		this.factory = factory;
	}

   	public boolean create(Object object) {
		session = factory.openSession();
		boolean created = false;
		try {
			transaction = session.beginTransaction();
			session.persist(object); 
			transaction.commit();
			created = true;
		} catch (HibernateException e) {
			if (transaction!=null) {
				transaction.rollback();
			} 
			e.printStackTrace();
		} finally {
			session.close(); 
		}
    	return created;
   	}

	public boolean read(List objects) {
    	boolean read = false;
        if(objects.isEmpty()) {
	        System.out.println("Table is empty.");
        } else {

        	session = factory.openSession();
        	try {
        		transaction = session.beginTransaction();
		        for(Object obj: objects) {
		        	System.out.print("\n" + obj);
		        }
		        read = true;
        	} catch (HibernateException e) {
			    if (transaction!=null) {
			    	transaction.rollback();
			    }
			    e.printStackTrace(); 
		    } finally {
		        session.close(); 
			}
        }
		return read;
    }

    public boolean update(Object object) {
    	boolean updated = false;
    	if(object==null) {
    		System.out.println("Object does not exist.");
    	} else {
	    	session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.update(object); 
		        transaction.commit();
		        updated = true;
			} catch (HibernateException e) {
			    if (transaction!=null) {
			    	transaction.rollback();
			    }
			    e.printStackTrace(); 
		    } finally {
		        session.close(); 
			}
    	}
    	return updated;
    }

    public boolean delete(Object object) {
	    boolean deleted = false;
	    if(object == null) {
	    	System.out.println("Object does not exist.");
	    } else {
		    session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.delete(object); 
			    transaction.commit();
			    deleted = true;
			} catch (HibernateException e) {
			    if (transaction!=null) {
			    	transaction.rollback();
			    }
			    e.printStackTrace(); 
		    } finally {
		        session.close(); 
			}
	    }
	    return deleted;
    }
}