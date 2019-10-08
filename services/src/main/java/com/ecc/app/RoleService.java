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

public class RoleService extends GeneralService {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public RoleService() {

	}

	public RoleService(SessionFactory factory) {
		this.factory = factory;
	}

	public boolean create(Role role) {
		session = factory.openSession();
		boolean created = false;
		try {
			transaction = session.beginTransaction();
			session.save(role); 
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

	public boolean read() {
		boolean read = false;
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
	        Root<Role> root = criteriaQuery.from(Role.class);
	        criteriaQuery.select(root);
	        Query<Role> query = session.createQuery(criteriaQuery);
	        List<Role> roles = query.getResultList();
	        if(roles == null) {
		        System.out.println("Role Table is empty.");
	        } else {
		        for(Role role: roles) {
		        	System.out.print(role);
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

	public boolean update(Role role) {
    	boolean updated = false;
    	if(role==null) {
    		System.out.println("Role does not exist in database.");
    	} else {
	    	session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.update(role); 
		        transaction.commit();
		        updated = true;
		        //System.out.println("Successfully updated Object with ID: " + objectId);
			} catch (HibernateException e) {
			    if (transaction!=null) {
			    	transaction.rollback();
			    }
			    e.printStackTrace(); 
		    } catch (Exception exception) {
		    	System.out.println(exception);
		    } finally {
		        session.close(); 
			}
    	}
    	return updated;
    }

	public boolean delete(Integer roleId) {
	    boolean deleted = false;
	    Role role = getObjectWithId(roleId);
	    if(role == null) {
	    	System.out.println("Role with ID: " + roleId + " does not exist.");
	    } else {
		    session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.delete(role); 
			    transaction.commit();
			    deleted = true;
			} catch (HibernateException e) {
			    if (transaction!=null) {
			    	transaction.rollback();
			    }
			    e.printStackTrace(); 
		    } catch(Exception exception) {
		    	System.out.println(exception);	
		    } finally {
		        session.close(); 
			}
	    }
	    return deleted;
    }

    public Role getObjectWithId(Integer roleId) {
    	session = factory.openSession();
    	Role role = new Role();
	    try {
	        role = session.get(role.getClass(), roleId);
		} catch (HibernateException e) {
		    if (transaction!=null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace(); 
	    } finally {
	        session.close(); 
		}
		return role;
    }
}