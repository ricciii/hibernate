package com.ecc.app;
 
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Collections;
import java.lang.IllegalArgumentException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PersonServiceImpl implements PersonService {

	private ScannerUtil scanner;
	private GeneratorService generator;
	private DatabaseService database;

	public PersonServiceImpl(ScannerUtil scanner, GeneratorService generator, DatabaseService database) {
		this.scanner = scanner;
		this.generator = generator;
		this.database = database;
	}

	public PersonServiceImpl() {

	}

	public boolean createPerson() {
		String choice;
        Name name = generator.generateName();
    	Address address = generator.generateAddress();
    	GregorianCalendar dateOfBirth = generator.generateDateOfBirth();
    	float gwa = generator.generateGwa();
    	GregorianCalendar dateHired = generator.generateDateHired();
    	boolean currentlyEmployed = generator.generateCurrentlyEmployed();
    	Person person = new Person(name, address, dateOfBirth, gwa, dateHired, currentlyEmployed);
        System.out.print("Do you want to add a contact?\n1=YES, input anything for NO: ");
        choice = scanner.getString();
        if("1".equals(choice)) {
            HashSet contacts = new HashSet();
            contacts = generator.generateContacts();
            person.setContacts(contacts);
        }
        System.out.print("Do you want to add a role?\n1=YES, input anything for NO: ");
        choice = scanner.getString();
        if("1".equals(choice)) {
        	List<Role> availableRoles = database.getRolesAsList();
        	for(Role role: availableRoles) {
        		System.out.println(role);
        	}
        	Integer roleId = scanner.getInt();
            Role role = (Role) database.getRoleWithId(roleId);
            if(role == null) {
            	System.out.print("Role does not exist.");
            } else {
            	HashSet roles = new HashSet();
	            roles.add(role);
	            person.setRoles(roles);
            }
        }
		boolean created = false;
		created = database.create(person);
		if(created) {
			System.out.print("Person successfully created.");
		} else {
			System.out.print("Person unsuccessfully created.");
		}
		return created;
	}

	public enum ListingOrder {
		DEFAULT, GWA, LASTNAME, DATEHIRED
	}

	public void readPerson(Person person) {
        try {
        	System.out.println(person);
        } catch(Exception e) {

        }
    }

	public void readPersons() {
		int choice;
    	boolean done = false;
    	List<Person> persons = new ArrayList<Person>();
		do {
    		System.out.println("\n<-- READING PERSON MENU -->\n");
	    	System.out.println("1=Default 2=BY GWA 3=BY LAST NAME 4=BY DATE HIRED 5=EXIT");
	    	System.out.print("Which reading do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				System.out.println("Reading all Person by Default: ");
	    				persons = database.getPersonsAsList(ListingOrder.DEFAULT);
	    				done = true;
	    				break;
	    			case 2:
	    				System.out.println("Reading all Person by ASC GWA: ");
	    				persons = database.getPersonsAsList(ListingOrder.GWA);
	    				done = true;
	    				break;
	    			case 3:
	    				System.out.println("Reading all Person by ASC Last Name: ");
	    				persons = database.getPersonsAsList(ListingOrder.LASTNAME);
	    				done = true;
	    				break;
	    			case 4:
	    				System.out.println("Reading all Person by DESC Date Hired: ");
	    				persons = database.getPersonsAsList(ListingOrder.DATEHIRED);
	    				done = true;
						break;
	    			case 5:
	    				done = true;
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception exception) {
	    		System.out.print(exception);
	    	}
    	} while(done==false);
		System.out.println("\n<--- READING PERSON TABLE --->\n");
		for(Person person: persons) {
			System.out.println(person);
		}
	}

	public boolean updatePerson() {
		boolean updated = false;
		boolean done = false;
		boolean skip = false;
		System.out.print("Input the ID of the Person you want to update: ");
		Integer personId = generator.generateId();
		Person person = (Person) database.getPersonWithId(personId);
		if(person == null) {
			System.out.print("Person with ID: " + personId + " does not exist.");
			updated = false;
		} else {
			int choice;
			do {
	    		System.out.println("\n<-- UPDATE PERSON MENU -->\n");
	    		System.out.println("Person Information: ");
	    		List<Person> persons = database.getPersonsAsList(ListingOrder.DEFAULT);
	    		readPerson(person);
		    	System.out.print("1=Name 2=Address 3=Date of Birth 4=GWA 5=Date Hired\n" + 
		    		"6=Currently Employed 7=Contact Info 8=Roles 9=EXIT\n");
		    	System.out.print("Which field do you want to update: ");
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				Name name = generator.generateName();
	    				person.setName(name);
	    				done = true;
	    				break;
	    			case 2:
	    				Address address = generator.generateAddress();
	    				person.setAddress(address); 
	    				done = true;
	    				break;
	    			case 3:
	    				GregorianCalendar dateOfBirth = generator.generateDateOfBirth();
	    				person.setDateOfBirth(dateOfBirth);
	    				done = true;
	    				break;
	    			case 4:
	    				float gwa = generator.generateGwa();
	    				person.setGwa(gwa); 
	    				done = true;
	    				break;
	    			case 5:
	    				GregorianCalendar dateHired = generator.generateDateHired();
	    				person.setDateHired(dateHired);
	    				done = true;
	    				break;
	    			case 6:
	    				boolean currentlyEmployed = generator.generateCurrentlyEmployed();
	    				person.setCurrentlyEmployed(currentlyEmployed);
	    				done = true;
	    				break;
	    			case 7:
	    				person = updatePersonContacts(person);
	    				done = true;
	    				break;
	    			case 8:
	    				person = updatePersonRoles(person);
	    				done = true;
	    				break;
	    			case 9:
	    				skip = true;
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    				skip = true;
	    		}
				if(skip!=true) {
					updated = database.update(person);
					if(updated) {
						System.out.println("Person successfully updated.");
					} else {
						System.out.println("Person unsuccessfully updated.");
					}
				}
				skip = false; 
	    	} while(done==false);
		} 
		return updated;
	}

	public Person updatePersonContacts(Person person) {
		int choice;
    	Integer contactId = null;
    	boolean done = false;
    	
    	do {
    		readPerson(person);
    		System.out.println("<-- UPDATE PERSON CONTACTS MENU -->\n");
	    	System.out.println("1=Add Contact 2=Delete Contact 3=EXIT");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				HashSet<Contact> contacts = generator.generateContacts();
	    				if(contacts!=null || !contacts.isEmpty()) {
	    					person.getContacts().addAll(contacts);
	    				}
	    				done = true;
	    				break;
	    			case 2:
	    				System.out.print("Input the ID of the contact you want to delete: ");
	    				contactId = generator.generateId();
	    				Contact contact = (Contact) database.getContactWithId(contactId);
	    				if(contact==null) {
	    					System.out.println("Contact does not exist");
	    				} else {
	    					person.getContacts().remove(contact);	
	    				}
	    				done = true;
	    				break;
	    			case 3:
	    				done = true;
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception exception) {
	    		done = true;
	    		System.out.print(exception);
	    	}
    	} while(done==false);
    	return person;
	}

	public Person updatePersonRoles(Person person) {
		int choice;
    	Integer roleId = null;
    	boolean done = false;
    	do {
    		readPerson(person);
    		System.out.println("<-- UPDATE PERSON ROLES MENU -->\n");
	    	System.out.println("1=Assign Role 2=Unassign Role 3=EXIT");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				List<Role> roles = database.getRolesAsList();
	    				for(Role role: roles) {
	    					System.out.println(role);
	    				}
	    				System.out.print("Choose the role ID you want to assign: ");
	    				roleId = scanner.getInt();
	    				Role assign = (Role) database.getRoleWithId(roleId);
	    				person.getRoles().add(assign);
	    				done = true;
	    				break;
	    			case 2:
	    				System.out.print("Input the ID of the role you want to delete: ");
	    				roleId = generator.generateId();
	    				Role role = (Role) database.getRoleWithId(roleId);
	    				if(role==null) {
	    					System.out.println("Role does not exist");
	    				} else {
	    					person.getRoles().remove(role);
	    				}
	    				done = true;
	    				break;
	    			case 3:
	    				done = true;
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception exception) {
	    		done = true;
	    		System.out.print(exception);
	    	}
    	} while(done==false);
    	return person;
	}

	public boolean deletePerson() {
		boolean deleted = false;
		Integer personId = generator.generateId();
		Person person = (Person) database.getPersonWithId(personId);
		deleted = database.delete(person);
		if(deleted) {
			System.out.print("Person successfully deleted.");
		} else {
			System.out.print("Person was deleted unsuccessfully.");
		}
		return deleted;
	} 
}