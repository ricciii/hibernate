package com.ecc.app;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.hibernate.Criteria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;

public class DatabaseServiceImpl implements DatabaseService {
	
	private static SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public DatabaseServiceImpl() {

	}

	public void start() {
		try {
	        factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	    }
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
		    } catch(Exception exception) {
		    	System.out.println(exception);
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
		    } catch(Exception exception) {
		    	System.out.println(exception);
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
		    } catch(Exception exception) {
		    	System.out.println(exception);
		    } finally {
		        session.close(); 
			}
	    }
	    return deleted;
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
	    } catch(Exception exception) {
		    System.out.println(exception);
		} finally {
	        session.close(); 
		}
		return person;
    }

	public Contact getContactWithId(Integer contactId) {
    	session = factory.openSession();
    	Contact contact = new Contact();
	    try {
	        contact = (Contact) session.get(Contact.class, contactId);
		} catch (HibernateException e) {
		    if (transaction!=null) {
		    	transaction.rollback();
		    }
		    e.printStackTrace(); 
	    } catch(Exception exception) {
		    System.out.println(exception);
		}finally {
	        session.close(); 
		}
		return contact;
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
	    } catch(Exception exception) {
		    System.out.println(exception);
		} finally {
	        session.close(); 
		}
		return role;
    }

    public List<Person> getPersonsAsList(PersonServiceImpl.ListingOrder order) {
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
		} catch(Exception exception) {
		    System.out.println(exception);
		} finally {
			session.close(); 
		}
    	return persons;
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
		} catch(Exception exception) {
		    System.out.println(exception);
		} finally {
			session.close(); 
		}
    	return roles;
    }

    public boolean removeRoleFromPersons(Role role) {
    	boolean removed = false;
    	session = factory.openSession();
	    try {
	    	transaction = session.beginTransaction();
	    	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
	        Root<Person> root = criteriaQuery.from(Person.class);
	        criteriaQuery.select(root);
	        Query<Person> query = session.createQuery(criteriaQuery);
	        List<Person> persons = query.getResultList();
	    	for (Person person : persons) {
	    		person.getRoles().remove(role);
	    	}
		    transaction.commit();
		    removed = true;
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
		return removed;
    }

}