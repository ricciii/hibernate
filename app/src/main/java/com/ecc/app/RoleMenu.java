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

public class RoleMenu implements Menu {
	
	private RoleService roleService;
	private ScannerUtil scanner;
	private GeneratorService generator;

	public RoleMenu(RoleService roleService, ScannerUtil scanner, GeneratorService generator) {
    	this.roleService = roleService;
    	this.scanner = scanner;
    	this.generator = generator;
	}

	public RoleMenu() {}

	public void start() {
		int choice;
    	String string;
    	Integer roleId = null;
    	boolean done = false;
    	Role role = new Role();
    	do {
    		System.out.println("\n<--- ROLES TABLE --->\n");
	    	System.out.println("1=CREATE 2=READ 3=UPDATE 4=DELETE 5=BACK");
	    	System.out.print("What do you want to do: ");
	    	try {
	    		choice = scanner.getInt();
	    		switch(choice) {
	    			case 1:
	    				role = generator.generateRole(scanner);
	    				boolean created = roleService.create(role);
	    				if(created==false) {
	    					System.out.println("Creating of Role was unsuccessful.");
	    				} else {
	    					System.out.println("Successfully created a Role.");
	    				}
	    				break;
	    			case 2:
	    				System.out.println("\n<--- READING ROLE TABLE --->\n");
	    				roleService.read();
	    				break;
	    			case 3:
	    				System.out.print("Input the ID of the Role you want to update: ");
	    				roleId = generator.generateId(scanner);
	    				role = (Role) roleService.getObjectWithId(roleId);
	    				if(role == null) {
	    					System.out.print("Role with ID: " + roleId + " does not exist.");
	    				} else {
	    					//update(role);
	    				}
	    				break;
	    			case 4:
	    				System.out.print("Input the ID of the Role you want to delete: ");
	    				roleId = generator.generateId(scanner);
	    				boolean deleted = roleService.delete(roleId);
	    				if(deleted) {
	    					System.out.print("Role successfully deleted.");
	    				} else {
	    					System.out.print("Role unsuccessfully deleted.");
	    				}
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
}