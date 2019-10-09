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

public class Main {
	public static void main(String[] args) {
		SessionFactory factory;
		try {
	        factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	    }
	    PersonService personService = new PersonService(factory);
	    RoleService roleService = new RoleService(factory);
        InputProvider scanner = new ScannerUtil();
        GeneratorService generator = new GeneratorService(scanner); 
        Menu personMenu = new PersonMenu(personService, roleService, scanner, generator);
        Menu roleMenu = new RoleMenu(roleService, scanner, generator);
        Menu mainMenu = new MainMenu(scanner, personMenu, roleMenu);
        mainMenu.start();
    }
}