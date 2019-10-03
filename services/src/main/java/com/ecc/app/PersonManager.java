package com.ecc.app;

import java.util.*;
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class PersonManager {
	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	public PersonManager() {

	}

	public PersonManager(SessionFactory factory) {
		this.factory = factory;
	}

	public Person createPersonPrompt(Scanner scanner) {
		Person person = new Person();
		try {
			System.out.println("CREATING PERSON...");
			System.out.println("\n<--- Name --->\n");
			String string;
			Name name = new Name();
	        System.out.print("Input last name: ");
	        scanner.nextLine();
	        string = scanner.nextLine();
	        name.setLastName(string);
	        System.out.print("Input first name: ");
	        string = scanner.nextLine();
	        name.setFirstName(string);
	        System.out.print("Input middle name: ");
	        string = scanner.nextLine();
	        name.setMiddleName(string);
	        System.out.print("Input name suffix: ");
	        string = scanner.nextLine();
	        name.setSuffix(string);
	        person.setName(name);
	        System.out.println("\n<--- Address --->\n");
	        Address address = new Address();
	        System.out.print("Input street: ");
	        string = scanner.nextLine();
	        address.setStreet(string);
	        System.out.print("Input barangay: ");
	        string = scanner.nextLine();
	        address.setBarangay(string);
	        System.out.print("Input municipality: ");
	        string = scanner.nextLine();
	        address.setMunicipality(string);
	        System.out.print("Input zip code: ");
	        string = scanner.nextLine();
	        address.setZipCode(string);
	        person.setAddress(address);
	        System.out.println("\n<--- Date of Birth --->\n");
	        System.out.print("Input year: ");
	        int year = scanner.nextInt();
	        System.out.print("Input month: "); 
	        int month = scanner.nextInt();
	        System.out.print("Input date of month: ");
	        int dayOfMonth = scanner.nextInt();
	        GregorianCalendar dateOfBirth = new GregorianCalendar(year, month, dayOfMonth);
	        person.setDateOfBirth(dateOfBirth);
	        System.out.println("\n<--- GWA --->\n");
	        System.out.print("GWA: ");
	        float gwa = scanner.nextFloat();
	        person.setGwa(gwa);
	        System.out.println("\n<--- Date Hired --->\n");
	        System.out.print("Input year: ");
	        year = scanner.nextInt();
	        System.out.print("Input month: "); 
	        month = scanner.nextInt();
	        System.out.print("Input date of month: ");
	        dayOfMonth = scanner.nextInt();
	        GregorianCalendar dateHired = new GregorianCalendar(year, month, dayOfMonth);
	        person.setDateHired(dateHired);
	        System.out.println("\n<--- Employment --->\n");
	        System.out.print("Currently employed: ");
	        Boolean currentlyEmployed = scanner.nextBoolean();
	        person.setCurrentlyEmployed(currentlyEmployed);
		} catch(Exception e) {
			e.printStackTrace();
		}
        return person;
	}

   	public Integer createPerson(Person person) {

		session = factory.openSession();
		Integer id = null;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(person); 
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction!=null) {
				transaction.rollback();
			} 
			e.printStackTrace();
		} finally {
			session.close(); 
		}
    return id;
   	}

	public Person addContactPrompt(Scanner scanner, Person person) {
		HashSet set = new HashSet();
		ContactInfo contactInfo;
		String string;
		boolean done = false;
		do {
			System.out.println("\n<--- Contact Information --->\n");
			contactInfo = new ContactInfo();
			System.out.println("Types: Mobile, Landline, Email");
			System.out.print("Input Type: ");
			scanner.nextLine();
			string = scanner.nextLine();
			contactInfo.setType(string);
			System.out.print("Input contact detail: ");
			string = scanner.nextLine();
			contactInfo.setContactInfo(string);
			set.add(contactInfo);
			System.out.println("Do you want to add more?");
			System.out.print("1=YES, Input anything for NO: "); 
			string = scanner.nextLine(); // FIX THIS. WONT RECOGNZE 1
			if(string != "1") {
			 	done = true;
			} 
		} while(done==false);
		person.setContactInfo(set);
		return person;
	}

	public Person addRolePrompt(Scanner scanner, Person person) {
		HashSet set = new HashSet();
		ContactInfo contactInfo;
		String string;
		boolean done = false;
		do {
			System.out.println("\n<--- Contact Information --->\n");
			contactInfo = new ContactInfo();
			System.out.println("Types: Mobile, Landline, Email");
			System.out.print("Input Type: ");
			scanner.nextLine();
			string = scanner.nextLine();
			contactInfo.setType(string);
			System.out.print("Input contact detail: ");
			string = scanner.nextLine();
			contactInfo.setContactInfo(string);
			set.add(contactInfo);
			System.out.println("Do you want to add more?");
			System.out.print("1=YES, Input anything for NO: "); 
			string = scanner.nextLine(); // FIX THIS. WONT RECOGNZE 1
			if(string != "1") {
			 	done = true;
			} 
		} while(done==false);
		person.setContactInfo(set);
		return person;
	}
}