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
			session.save(object); 
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

	public boolean read(Object object) {
    	boolean read = false;
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(object.getClass());
	        Root root = criteriaQuery.from(object.getClass());
	        criteriaQuery.select(root);
	        Query query = session.createQuery(criteriaQuery);
	        List objects = query.getResultList();
	        if(objects == null) {
		        System.out.println("Table is empty.");
	        } else {
	        	System.out.println("\n<--- READING TABLE --->\n");
		        for(Object obj: objects) {
		        	System.out.print(obj);
		        }
		        read = true;
	        }
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return read;
    }

    public boolean update(Object object, Integer objectId) {
    	boolean updated = false;
    	if(object==null) {
    		System.out.println("Object with ID:" + objectId + " does not exist.");
    	} else {
	    	session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.update(object); 
		        transaction.commit();
		        updated = true;
		        System.out.println("Successfully updated Object with ID: " + objectId);
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

    public boolean delete(Object object, Integer objectId) {
	    boolean deleted = false;
	    if(object == null) {
	    	System.out.println("Object with ID: " + objectId + " does not exist.");
	    } else {
		    session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.delete(object); 
			    transaction.commit();
			    deleted = true;
			    System.out.println("Successfully deleted Object with ID: " + objectId);	
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

    public Object getObjectWithId(Integer objectId) {
    	session = factory.openSession();
    	Object object = new Person();
	    try {
	        object = session.get(object.getClass(), objectId);
		} catch (HibernateException e) {
		    if (transaction!=null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace(); 
	    } finally {
	        session.close(); 
		}
		return object;
    }
}