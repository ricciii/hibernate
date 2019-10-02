package com.ecc.app;

import java.util.Scanner;
import java.time.LocalDate;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

// a spike for persisting an object: 

    public static void main( String[] args )
    {
		SessionFactory factory;
		try {
	        factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	    }
		
		float gwa=4;
		Boolean currentlyEmployed = true;
		Scanner scanner = new Scanner(System.in);
        PersonManager personManager = new PersonManager(factory);
        Name name = new Name("Fosgate","Reichney", "Mantos", "II");
        Address address = new Address("Topaz Rd.","Ortigas", "Pasig", "2087");
        LocalDate dateOfBirth = LocalDate.of(1998, 9, 21);
        LocalDate dateHired = LocalDate.of(2019, 6, 6);
        Person person = new Person(name, address, dateOfBirth, gwa, dateHired, currentlyEmployed);
        Integer id = personManager.addPerson(person);
    }
}
