package com.ecc.app;

import java.util.Scanner;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.HashSet;

public class PersonMenu implements Menu {
	
	private PersonService personService;
	private RoleService roleService;
	private ScannerUtil scanner;
	private GeneratorService generator;

	public PersonMenu(PersonService personService, RoleService roleService, ScannerUtil scanner, GeneratorService generator) {
		this.personService = personService;
    	this.roleService = roleService;
    	this.scanner = scanner;
    	this.generator = generator;
	}

	public void start() {
		int choice;
    	String string;
    	boolean done = false;
    	Integer personId = null;
    	Integer roleId = null;
    	Person person;
    	Role role;
    	do {
    		System.out.println("\n<--- PERSON TABLE --->\n");
	    	System.out.println("1=CREATE 2=READ 3=UPDATE 4=DELETE 5=BACK");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				person = generator.generatePerson(scanner, roleService);
	    				boolean created = personService.create(person);
	    				if(created) {
	    					System.out.print("Person successfully created.");
	    				} else {
	    					System.out.print("Person unsuccessfully created.");
	    				}
	    				break;
	    			case 2:
	    				showReadAllPersonByMenu();
	    				break;
	    			case 3:
	    				System.out.print("Input the ID of the Person you want to update: ");
	    				personId = generator.generateId(scanner);
	    				person = personService.getPersonWithId(personId);
	    				if(person == null) {
	    					System.out.print("Person with ID: " + personId + " does not exist.");
	    				} else {
	    					showUpdatePersonMenu(person, personId);
	    				}
	    				break;
	    			case 4:
	    				System.out.print("Input the ID of the Person you want to delete: ");
	    				personId = generator.generateId(scanner);
	    				personService.deletePerson(personId);
	    				break;
	    			case 5:
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception e) {
	    		System.out.print(e);
	    	}
    	} while(done==false);
	}

	public void showReadAllPersonByMenu() {
    	int choice;
    	boolean done = false;
    	do {
    		System.out.println("\n<-- READING PERSON MENU -->\n");
	    	System.out.println("1=Default 2=BY GWA 3=BY LAST NAME 4=BY DATE HIRED 5=EXIT");
	    	System.out.print("Which reading do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				System.out.println("Reading all Person by Default: ");
	    				personService.read(PersonService.ReadingOrder.DEFAULT);
	    				break;
	    			case 2:
	    				System.out.println("Reading all Person by GWA: ");
	    				personService.read(PersonService.ReadingOrder.GWA);
	    				break;
	    			case 3:
	    				System.out.println("Reading all Person by Last Name: ");
	    				personService.read(PersonService.ReadingOrder.LASTNAME);
	    				break;
	    			case 4:
	    				System.out.println("Reading all Person by Date Hired: ");
	    				personService.read(PersonService.ReadingOrder.DATEHIRED);
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
    }

    // THE PERSON HERE SHOULD EXIST ALREADY
    public void showUpdatePersonMenu(Person person, Integer personId) {
    	int choice;
    	boolean done = false;
    	do {
    		System.out.println("\n<-- UPDATE PERSON MENU -->\n");
    		System.out.println("Person Information: ");
    		personService.readPerson(personId);
	    	System.out.print("1=Name 2=Address 3=Date of Birth 4=GWA 5=Date Hired\n" + 
	    		"6=Currently Employed 7=Contact Info 8=Roles 9=EXIT\n");
	    	System.out.print("Which field do you want to update: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				Name name = generator.generateName(scanner);
	    				person.setName(name);
	    				break;
	    			case 2:
	    				Address address = generator.generateAddress(scanner);
	    				person.setAddress(address);
	    				break;
	    			case 3:
	    				GregorianCalendar dateOfBirth = generator.generateDateOfBirth(scanner);
	    				person.setDateOfBirth(dateOfBirth);
	    				break;
	    			case 4:
	    				float gwa = generator.generateGwa(scanner);
	    				person.setGwa(gwa);
	    				break;
	    			case 5:
	    				GregorianCalendar dateHired = generator.generateDateHired(scanner);
	    				person.setDateHired(dateHired);
	    				break;
	    			case 6:
	    				boolean currentlyEmployed = generator.generateCurrentlyEmployed(scanner);
	    				person.setCurrentlyEmployed(currentlyEmployed);
	    				break;
	    			case 7:
	    				showUpdatePersonContactsMenu(personId);
	    				break;
	    			case 8:
	    				showUpdatePersonRolesMenu(personId);
	    				break;
	    			case 9:
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception exception) {
	    		System.out.print(exception);
	    	} finally {
	    		boolean updated = personService.updatePerson(person);
	    		if(updated) {
	    			System.out.println("Person successfully updated.");
	    		} else {
	    			System.out.println("Person unsuccessfully updated.");
	    		}
	    	}
    	} while(done==false);
    }

    public void showUpdatePersonContactsMenu(Integer personId) {
    	int choice;
    	Integer contactId = null;
    	boolean done = false;
    	Person person = personService.getPersonWithId(personId);
    	personService.readPerson(personId);
    	do {
    		System.out.println("\n<-- UPDATE PERSON CONTACTS MENU -->\n");
    		System.out.println("Updating Contacts of Person with ID: " + personId);
	    	System.out.println("1=Add Contact 2=Delete Contact 3=EXIT");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				System.out.println("Adding contact: ");
	    				HashSet contacts = generator.generateContacts(scanner);
	    				boolean added = personService.addContacts(personId, contacts);
	    				if(added) {
							System.out.println("Successfully added contact/s.");
	    				} else {
	    					System.out.println("Contact/s was unsuccessfully added.");
	    				}
	    				break;
	    			case 2:
	    				System.out.print("Input the ID of the contact you want to delete: ");
	    				contactId = generator.generateId(scanner);
	    				boolean deleted = personService.deleteContact(personId, contactId);
	    				if(deleted) {
	    					System.out.println("Successfully deleted contact.");
	    				} else {
	    					System.out.println("Contact was unsuccessfully deleted.");
	    				}
	    				break;
	    			case 3:
	    				done = true;
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception exception) {
	    		System.out.print(exception);
	    	}
    	} while(done==false);
    }

    public void showUpdatePersonRolesMenu(Integer personId) {
    	int choice;
    	Integer roleId = null;
    	boolean done = false;
    	Person person = personService.getPersonWithId(personId);
    	Role role = new Role();
    	personService.readPerson(personId);
    	do {
    		System.out.println("\n<-- UPDATE PERSON ROLES MENU -->\n");
    		System.out.println("Updating Roles of Person with ID: " + personId);
	    	System.out.println("1=Assign Role 2=Unassign Role 3=EXIT");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				System.out.println("Assigning role... ");
	    				System.out.println("Available Roles: ");
	    				roleService.read();
	    				roleId = generator.generateId(scanner);
	    				role = roleService.getObjectWithId(roleId);
	    				if (role==null) {
	    					System.out.println("Role does not exist.");
	    				} else {

	    					// boolean assigned = personService.assignRole(person, scanner, generator, roleService);
	    					// System.out.println(assigned);
	    				}
	    				
	    				break;
	    			case 2:
	    				System.out.println("Unassigning role: ");
	    				break;
	    			case 3:
	    				done = true;
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception exception) {
	    		System.out.print(exception);
	    	}
    	} while(done==false);
    }
}