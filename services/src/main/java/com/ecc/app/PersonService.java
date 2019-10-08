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

public class PersonService {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public PersonService() {

	}

	public PersonService(SessionFactory factory) {
		this.factory = factory;
	}

   	public boolean create(Person person) {
		session = factory.openSession();
		boolean created = false;
		try {
			transaction = session.beginTransaction();
			session.save(person); 
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

	// public void addRole(Integer personId, Role role) {
	// 	Person person = getPersonWithId(personId);
	// 	if (person==null) {
	// 		System.out.println("Person with ID:" + personId + " does not exist.");
	// 	} else {
	// 		person.getRoles().add(role);
	// 		updatePerson(person);
	// 	}
	// }

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

	public boolean readAllPerson() {
		boolean readAll = false;
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
	        Root<Person> root = criteriaQuery.from(Person.class);
	        criteriaQuery.select(root);
	        Query<Person> query = session.createQuery(criteriaQuery);
	        List<Person> people = query.getResultList();
	        if(people == null) {
		        System.out.println("Person Table is empty.");
	        } else {
	        	System.out.println("\n<--- READING PERSON TABLE --->\n");
		        for(Person person: people) {
		        	System.out.print(person);
		        }
		        readAll = true;
	        }
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return readAll;
    }

    public boolean readAllPersonByLastName() {
		boolean readAll = false;
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
	        Root<Person> root = criteriaQuery.from(Person.class);
	        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("name")));
	        Query<Person> query = session.createQuery(criteriaQuery);
	        List<Person> people = query.getResultList();
	        if(people == null) {
		        System.out.println("Person Table is empty.");
	        } else {
	        	System.out.println("\n<--- READING PERSON TABLE --->\n");
		        for(Person person: people) {
		        	System.out.print(person);
		        }
		        readAll = true;
	        }
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return readAll;
    }

    public boolean readAllPersonByDateHired() {
		boolean readAll = false;
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
	        Root<Person> root = criteriaQuery.from(Person.class);
	        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dateHired")));
	        Query<Person> query = session.createQuery(criteriaQuery);
	        List<Person> people = query.getResultList();
	        if(people == null) {
		        System.out.println("Person Table is empty.");
	        } else {
	        	System.out.println("\n<--- READING PERSON TABLE --->\n");
		        for(Person person: people) {
		        	System.out.print(person);
		        }
		        readAll = true;
	        }
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return readAll;
    }

    public boolean readAllPersonByGWA() {
		boolean readAll = false;
    	try {
	    	session = factory.openSession();
	        transaction = session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
	        Root<Person> root = criteriaQuery.from(Person.class);
	        criteriaQuery.select(root);
	        Query<Person> query = session.createQuery(criteriaQuery);
	        List<Person> people = query.getResultList();
	        Collections.sort(people);
	        if(people == null) {
		        System.out.println("Person Table is empty.");
	        } else {
	        	System.out.println("\n<--- READING PERSON TABLE --->\n");
		        for(Person person: people) {
		        	System.out.print(person);
		        }
		        readAll = true;
	        }
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return readAll;
    }

    public boolean deletePerson(Integer personId) {
	    boolean deleted = false;
	    Person person = getPersonWithId(personId);
	    if(person == null) {
	    	System.out.println("Person with ID: " + personId + " does not exist.");
	    } else {
		    session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.delete(person); 
			    transaction.commit();
			    deleted = true;
			    System.out.println("Successfully deleted Person with ID: " + personId);	
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

    public boolean updatePerson(Person person) {
    	boolean updated = false;
    	if(getPersonWithId(person.getId())==null) {
    		System.out.println("Person with ID:" + person.getId() + " does not exist.");
    	} else {
	    	session = factory.openSession();
		    try {
		    	transaction = session.beginTransaction();
		        session.update(person); 
		        transaction.commit();
		        updated = true;
		        System.out.println("Successfully updated Person with ID: " + person.getId());
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

    // public boolean assignRole(Person person, ScannerUtil scanner, GeneratorUtil generator, RoleService service) {
    // 	boolean assigned = false;
    // 	System.out.println("List of available Roles: ");
    // 	service.read();
    // 	System.out.print("Input ID of role you want to assign:: ");
    // 	Integer roleId = generator.generateId(scanner);
    // 	HashSet roles = new HashSet();
    // 	Role role = service.getObjectWithId(roleId);
    // 	if(role!=null) {
		  //       roles.add(role);
    // 			person.setRoles(roles);
		  //       assigned = true;
    // 	}
    // 	return assigned;
    // }

    // public Person assignRole(Person person, Role role) {
    // 	assigned = false;
    // 	System.out.println("List of available Roles: ");
    // 	service.read();
    // 	System.out.print("Input ID of role you want to assign:: ");
    // 	Integer roleId = generator.generateId(scanner);
    // 	HashSet roles = new HashSet();
    // 	Role role = service.getObjectWithId(roleId);
    // 	if(role!=null) {
		  //       roles.add(role);
    // 			person.setRoles(roles);
		  //       assigned = true;
    // 	}
    // 	return person;
    // }

    public boolean unassignRole(Integer personId, Role role) {
    	boolean updated = false;
    	Person person = getPersonWithId(personId);
    	if(getPersonWithId(person.getId())==null || role == null) {
    		System.out.println("Person with ID:" + person.getId() + " does not exist.");
    	} else {
	    	session = factory.openSession();
		    try {
		    	person.getRoles().remove(role);
		    	transaction = session.beginTransaction();
		        session.update(person); 
		        transaction.commit();
		        updated = true;
		        System.out.println("Successfully updated Person with ID: " + person.getId());
			} catch (HibernateException e) {
			    if (transaction!=null) {
			    	transaction.rollback();
			    }
			    e.printStackTrace(); 
		    } catch (Exception exception) {
		    	System.out.println(exception);
		    } 
		    finally {
		        session.close(); 
			}
    	}
    	return updated;

    }
}