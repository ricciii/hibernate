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
import java.util.ArrayList;

public class RoleService extends GeneralService {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public RoleService() {

	}

	public RoleService(SessionFactory factory) {
		super(factory);
		this.factory = factory;
	}

    public Role getRoleWithId(Integer roleId) {
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

    public List<Role> getRolesAsList() {
    	List<Role> roles = new ArrayList<Role>();
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
	        Root<Role> root = criteriaQuery.from(Role.class);
	        criteriaQuery.select(root);
	        Query<Role> query = session.createQuery(criteriaQuery);
	        roles = query.getResultList();
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
    	return roles;
    }
}