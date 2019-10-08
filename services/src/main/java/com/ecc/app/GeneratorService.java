package com.ecc.app;

import java.util.Set;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.InputMismatchException;

public class GeneratorService {
    
    ScannerUtil scanner;

    public GeneratorService(ScannerUtil scanner) {
        this.scanner = scanner;
    }

    public Person generatePerson(ScannerUtil scanner) {
    	String string;
        Name name = generateName(scanner);
    	Address address = generateAddress(scanner);
    	GregorianCalendar dateOfBirth = generateDateOfBirth(scanner);
    	float gwa = generateGwa(scanner);
    	GregorianCalendar dateHired = generateDateHired(scanner);
    	boolean currentlyEmployed = generateCurrentlyEmployed(scanner);
    	Person person = new Person(name, address, dateOfBirth, gwa, dateHired, currentlyEmployed);
        HashSet contacts = new HashSet();
        System.out.print("Do you want to add a contact?\n1=YES, input anything for NO: ");
        try {
            string = scanner.getString();
            if("1".equals(string)) {
                contacts = generateContacts(scanner);
                person.setContacts(contacts);
            }
        } catch(Exception e) {

        }
    	return person;
    }

    public Integer generateId(ScannerUtil scanner) {
    	Integer id = null;
    	try {
    		id = scanner.getInt();
    	} catch(InputMismatchException mismatch) {
    		System.out.println("Input should be of Integer type.");
    	} catch(Exception e) {
    		System.out.println("Something went wrong."); 
    	}
    	return id;
    }

    public Name generateName(ScannerUtil scanner) {
    	String string;
    	Name name = new Name();
    	System.out.println("\n<--- Name --->\n");
        System.out.print("Input last name: ");
        string = scanner.getString();
        name.setLastName(string);
        System.out.print("Input first name: ");
        string = scanner.getString();
        name.setFirstName(string);
        System.out.print("Input middle name: ");
        string = scanner.getString();
        name.setMiddleName(string);
        System.out.print("Input name suffix: ");
        string = scanner.getString();
        name.setSuffix(string);
        return name;
    }

    public Address generateAddress(ScannerUtil scanner) {
    	String string;
    	Address address = new Address();
    	System.out.println("\n<--- Address --->\n");
        System.out.print("Input street: ");
        string = scanner.getString();
        address.setStreet(string);
        System.out.print("Input barangay: ");
        string = scanner.getString();
        address.setBarangay(string);
        System.out.print("Input municipality: ");
        string = scanner.getString();
        address.setMunicipality(string);
        System.out.print("Input zip code: ");
        string = scanner.getString();
        address.setZipCode(string);
        return address;
    }

    public GregorianCalendar generateDateOfBirth(ScannerUtil scanner) {
    	System.out.println("\n<--- Date of Birth --->\n");
        System.out.print("Input year: ");
        int year = scanner.getInt();
        System.out.print("Input month: "); 
        int month = scanner.getInt();
        System.out.print("Input date of month: ");
        int dayOfMonth = scanner.getInt();
        GregorianCalendar dateOfBirth = new GregorianCalendar(year, month-1, dayOfMonth);
        return dateOfBirth;
    }

    public GregorianCalendar generateDateHired(ScannerUtil scanner) {
    	System.out.println("\n<--- Date Hired --->\n");
        System.out.print("Input year: ");
        int year = scanner.getInt();
        System.out.print("Input month: "); 
        int month = scanner.getInt();
        System.out.print("Input date of month: ");
        int dayOfMonth = scanner.getInt();
        GregorianCalendar dateHired = new GregorianCalendar(year, month-1, dayOfMonth);
        return dateHired;
    }

    public float generateGwa(ScannerUtil scanner) {
    	System.out.println("\n<--- GWA --->\n");
        System.out.print("GWA: ");
        float gwa = scanner.getFloat();
        return gwa;
    }

    public boolean generateCurrentlyEmployed(ScannerUtil scanner) {
    	System.out.println("\n<--- Currently Employed --->\n");
        System.out.print("Currently Employed: ");
        boolean currentlyEmployed = scanner.getBoolean();
        return currentlyEmployed;
    }

    public Contact generateContact(ScannerUtil scanner) {
    	System.out.println("\n<--- Contact Information --->\n");
        Contact contact = new Contact();
        String string;
		int i = 1;
        for(Contact.ContactType type: Contact.ContactType.values()) {
            System.out.print((i++) + "=" + type + " ");
        }
		System.out.println();
        boolean done = false;
        do {
            try {
                System.out.print("\nInput type: ");
                int choice = scanner.getInt();
                switch(choice) {
                    case 1:
                        contact.setType(Contact.ContactType.MOBILE);
                        done = true;
                        break;
                    case 2:
                        contact.setType(Contact.ContactType.LANDLINE);
                        done = true; 
                        break;
                    case 3:
                        contact.setType(Contact.ContactType.EMAIL);
                        done = true;
                        break;
                    default: 
                        System.out.println("Not in the choices, try again.");
                }
            } catch(Exception e) {
                System.out.println("Not in the choices, try again.");
            }
        } while(done==false);
		System.out.print("Input contact detail: ");
		string = scanner.getString();
		contact.setContact(string);
		return contact;
    }

    public HashSet generateContacts(ScannerUtil scanner) {
        HashSet set = new HashSet();
        Contact contact;
        String string;
        boolean done = false;
        do {
            contact = generateContact(scanner);
            set.add(contact);
            System.out.println("Do you want to add more?");
            System.out.print("1=YES, Input anything for NO: "); 
            string = scanner.getString();
            if("1".equals(string) == false) {
                done = true;
            } 
        } while(done==false);
        return set;
    } 

    public Role generateRole(ScannerUtil scanner) {
        Role role = new Role();
        String string;
        System.out.print("Input: ");
        string = scanner.getString();
        role.setRole(string);
        return role;
    }

    public HashSet generateRoles(ScannerUtil scanner) {
		HashSet set = new HashSet();
		Role role;
        String string;
        boolean done = false;
		do {
			role = generateRole(scanner);
			set.add(role);
			System.out.println("Do you want to add more?");
			System.out.print("1=YES, Input anything for NO: "); 
			string = scanner.getString();
			if(string != "1") {
			 	done = true;
			} 
		} while(done==false);
		return set;
	}
}
