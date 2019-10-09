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
import java.util.Collections;
import java.lang.IllegalArgumentException;
import java.util.Comparator;
import java.util.ArrayList;

public class PersonService extends GeneralService {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public PersonService() {

	}

	public PersonService(SessionFactory factory) {
		super(factory);
		this.factory = factory;
	}

	public boolean addContacts(Integer personId, Set contacts) {
		boolean added = false;
		try {
	    	session = factory.openSession();
	    	transaction = session.beginTransaction();
	        Person person = (Person) session.get(Person.class, personId);
	        person.getContacts().addAll(contacts);
	        session.save(person); 
	        transaction.commit();
	        added = true;
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return added;
	}

	public boolean addRoles(Integer personId, Set roles) {
		boolean added = false;
		try {
	    	session = factory.openSession();
	    	transaction = session.beginTransaction();
	        Person person = (Person) session.get(Person.class, personId);
	        person.getRoles().addAll(roles);
	        session.save(person); 
	        transaction.commit();
	        added = true;
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return added;
	}

	public boolean deleteContact(Integer personId, Integer contactId) {
		boolean deleted = false;
		session = factory.openSession();
	    try {
	    	transaction = session.beginTransaction();
	    	Person person = (Person) session.get(Person.class, personId);
	    	Set contacts = person.getContacts();
	    	Set contactIds = new HashSet();
	    	for(Object object: contacts) {
	    		Contact contact = (Contact) object;
	    		contactIds.add(contact.getId());
	    	}
	    	if(contactIds.contains((int) contactId)) {
	    		Contact contact = (Contact) session.get(Contact.class, contactId);
	    		person.getContacts().remove(contact);
	    		session.delete(contact);
	        	transaction.commit();
	        	deleted = true;
	    	} else {
	    		System.out.println("Person does not have the contact.");
	    	}
		} catch(IllegalArgumentException illegal) {
    		System.out.println("Contact with ID:" + contactId + " does not exist.");
    	} catch (HibernateException e) {
		    if (transaction!=null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace(); 
	    } finally {
	        session.close(); 
		}
		return deleted;
	}

	public boolean deleteRole(Integer personId, Integer roleId) {
		boolean deleted = false;
		session = factory.openSession();
	    try {
	    	transaction = session.beginTransaction();
	    	Person person = (Person) session.get(Person.class, personId);
	    	Role role = (Role) session.get(Role.class, roleId);
	    	if(role != null) {
	    		person.getRoles().remove(role);
	    		session.save(person);
	        	transaction.commit();
	        	deleted = true;
	    	} else {
	    		System.out.println("Person does not have the role.");
	    	}
		} catch(IllegalArgumentException illegal) {
    		System.out.println("Role with ID:" + roleId + " does not exist.");
    	} catch (HibernateException e) {
		    if (transaction!=null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace(); 
	    } finally {
	        session.close(); 
		}
		return deleted;
	}

	public boolean readPerson(Integer personId) {
    	boolean read = false;
    	Person person = getPersonWithId(personId);
	    if(person == null) {
	    	System.out.println("Person with ID: " + personId + " does not exist.\n");
	    } else {
	    	session = factory.openSession();
		    try {
		        person = (Person) session.get(Person.class, personId); 
		        System.out.println(person);
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

    public Person getPersonWithId(Integer personId) {
    	session = factory.openSession();
    	Person person = new Person();
	    try {
	        person = (Person) session.get(Person.class, personId);
		} catch (HibernateException e) {
		    if (transaction!=null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace(); 
	    } finally {
	        session.close(); 
		}
		return person;
    }

    public enum ListingOrder {
		DEFAULT, GWA, LASTNAME, DATEHIRED
	}

    public List<Person> getPersonsAsList(ListingOrder order) {
    	List<Person> persons = new ArrayList<Person>();
    	try {
	    	boolean sortByGWA=false;
			session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
	        Root<Person> root = criteriaQuery.from(Person.class);
			switch(order) {
				case GWA:
					sortByGWA = true;
					criteriaQuery.select(root);
					break;
				case LASTNAME:
					criteriaQuery.orderBy(criteriaBuilder.asc(root.get("name")));
					break;
				case DATEHIRED:
					criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dateHired")));
					break;
				default:
					criteriaQuery.select(root);
			}
			Query<Person> query = session.createQuery(criteriaQuery);
	        persons = query.getResultList();
	        if(sortByGWA) {
	        	Collections.sort(persons);
	        }
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
    	return persons;
    }
}