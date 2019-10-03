package com.ecc.app;

import java.util.Scanner;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.GregorianCalendar;

public class App {

// a spike for persisting an object: 
	private static SessionFactory factory;
	private PersonManager personManager= new PersonManager(factory);
	private Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) {
		try {
	        factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	    }

        App app = new App();
        app.start();
        PersonManager personManager = new PersonManager(factory);
        
        
    }

    public void start() {
    	int choice;
    	boolean done = false;
    	do {
	    	System.out.println("1=Person 2=Roles 3=Exit");
	    	System.out.print("Which table do you want to access: ");
	    	try {
	    		choice = scanner.nextInt();
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
    	boolean done = false;
    	do {
    		System.out.println("\n<--- PERSON TABLE --->\n");
	    	System.out.println("1=CREATE 2=READ 3=UPDATE 4=DELETE 5=BACK");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.nextInt();
	    		switch(choice) {
	    			case 1:
	    				Person person = personManager.createPersonPrompt(scanner);
	    				person = personManager.addContactPrompt(scanner, person);
	    				System.out.print("Do you want to add a role?\n"+
	    					"1=YES, input anything for NO: ");
	    				try {
	    					choice = scanner.nextInt();
	    					if(choice==1) {
	    						person = personManager.addRolesPrompt(scanner, person);
	    					}
	    				} catch(Exception e) {}
	    				Integer id = personManager.createPerson(person);
	    				break;
	    			case 2:
	    				personManager.readPerson();
	    				break;
	    			case 3:
	    				 
	    				break;
	    			case 4:
	    				
	    				break;
	    			case 5:
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

    public void rolesTableMenu() {
    	
    }
}