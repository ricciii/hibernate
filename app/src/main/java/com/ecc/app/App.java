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

public class App {

	private static SessionFactory factory;
	private PersonManager personManager= new PersonManager(factory);
	private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
		try {
	        factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	    }
        App app = new App();
        app.start();
    }

    public void start() {
    	int choice;
    	boolean done = false;
    	do {
    		System.out.println("\n<-- MAIN MENU -->\n");
	    	System.out.println("1=Person 2=Roles 3=Exit");
	    	System.out.print("Which table do you want to access: ");
	    	try {
	    		choice = scanner.nextInt();
	    		scanner.nextLine();
	    		switch(choice) {
	    			case 1:
	    				personTableMenu();
	    				break;
	    			case 2:
	    				rolesTableMenu();
	    				break;
	    			case 3:
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception e) {
	    		System.out.print("Not in the choices. Try again.");
	    	}
    	} while(done==false);
    }

    public void personTableMenu() {
    	int choice;
    	String string;
    	boolean done = false;
    	Integer id = null;
    	do {
    		System.out.println("\n<--- PERSON TABLE --->\n");
	    	System.out.println("1=CREATE 2=READ 3=UPDATE 4=DELETE 5=BACK");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.nextInt();
	    		scanner.nextLine();
	    		switch(choice) {
	    			case 1:
	    				Person person = personManager.createPersonPrompt(scanner);
	    				person = personManager.addContactPrompt(scanner, person);
	    				System.out.print("Do you want to add a role?\n" +
	    					"1=YES, input anything for NO: ");
	    				try {
	    					string = scanner.nextLine();
	    					if(string.equals("1")) {
	    						person = personManager.addRolesPrompt(scanner, person);
	    					}
	    				} catch(Exception e) {}
	    				id = personManager.createPerson(person);
	    				System.out.print("Successfully created person with ID: " + id);
	    				break;
	    			case 2:
	    				personManager.readPerson();
	    				break;
	    			case 3:
	    				System.out.print("Input the ID of the Person you want to update: ");
	    				id = personManager.generateId(scanner);
	    				updatePersonMenu(id);
	    				break;
	    			case 4:
	    				System.out.print("Input the ID of the Person you want to delete: ");
	    				id = personManager.generateId(scanner);
	    				personManager.deletePerson(id);
	    				break;
	    			case 5:
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}

	    	} catch(InputMismatchException mismatch) {
	    		System.out.print("Not in the choices. Try again.");
	    	} catch(Exception e) {
	    		System.out.print(e);
	    	}
    	} while(done==false);
    }

    public void rolesTableMenu() {
    	
    }

    public void updatePersonMenu(Integer id) {
    	int choice;
    	boolean done = false;
    	Person person = personManager.getPersonWithId(id);
    	do {
    		System.out.println("\n<-- UPDATE PERSON MENU -->\n");
    		System.out.println("Updating Person with ID: " + id);
	    	System.out.print("1=Name 2=Address 3=Date of Birth 4=GWA\n" + 
	    		"5=Date Hired 6=Currently Employed 7=Contact Info 8=Roles\n" +
	    		"9=EXIT\n");
	    	System.out.print("Which field do you want to update: ");
	    	try {
	    		choice = scanner.nextInt();
	    		scanner.nextLine();
	    		switch(choice) {
	    			case 1:
	    				Name name = personManager.generateName(scanner);
	    				person.setName(name);
	    				personManager.updatePerson(person);
	    				break;
	    			case 2:
	    				Address address = personManager.generateAddress(scanner);
	    				person.setAddress(address);
	    				personManager.updatePerson(person);
	    				break;
	    			case 3:
	    				GregorianCalendar dateOfBirth = personManager.generateDateOfBirth(scanner);
	    				person.setDateOfBirth(dateOfBirth);
	    				personManager.updatePerson(person);
	    				break;
	    			case 4:
	    				float gwa = personManager.generateGwa(scanner);
	    				person.setGwa(gwa);
	    				personManager.updatePerson(person);
	    				break;
	    			case 5:
	    				GregorianCalendar dateHired = personManager.generateDateHired(scanner);
	    				person.setDateHired(dateHired);
	    				personManager.updatePerson(person);
	    				break;
	    			case 6:
	    				boolean currentlyEmployed = personManager.generateCurrentlyEmployed(scanner);
	    				person.setCurrentlyEmployed(currentlyEmployed);
	    				personManager.updatePerson(person);
	    				break;
	    			case 7:

	    				break;
	    			case 8:
	    				
	    				break;
	    			case 9:
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception e) {
	    		System.out.print("Not in the choices. Try again.");
	    	}
    	} while(done==false);
    }
}