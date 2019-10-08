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

public class MainMenu implements Menu {

	private PersonService personService;
	private RoleService roleService;
	private ScannerUtil scanner;
	private GeneratorService generator;
	private Menu personMenu;
	private Menu roleMenu;

    public MainMenu(PersonService personService, RoleService roleService, ScannerUtil scanner, 
    	GeneratorService generator, Menu personMenu, Menu roleMenu) {
    	this.personService = personService;
    	this.roleService = roleService;
    	this.scanner = scanner;
    	this.generator = generator;
    	this.personMenu = personMenu;
    	this.roleMenu = roleMenu;
    }

    public MainMenu() {

    }

    public void start() {
    	int choice;
    	boolean done = false;
    	do {
    		System.out.println("\n<-- MAIN MENU -->\n");
	    	System.out.println("1=Person 2=Roles 3=Exit");
	    	System.out.print("Which table do you want to access: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				personMenu.start();
	    				break;
	    			case 2:
	    				roleMenu.start();
	    				break;
	    			case 3:
	    				done = true; 
	    				break;
	    			default: 
	    				System.out.println("Not in the choices, try again.");
	    		}
	    	} catch(Exception e) {
	    		System.out.print("Not in the choices. Try again.");
	    	} finally {}
    	} while(done==false);
    }
}