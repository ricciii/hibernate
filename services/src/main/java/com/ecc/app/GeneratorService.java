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

    public GeneratorService() {
    }

    public Person generatePerson(RoleService roleService) {
    	String string;
        Name name = generateName();
    	Address address = generateAddress();
    	GregorianCalendar dateOfBirth = generateDateOfBirth();
    	float gwa = generateGwa();
    	GregorianCalendar dateHired = generateDateHired();
    	boolean currentlyEmployed = generateCurrentlyEmployed();
    	Person person = new Person(name, address, dateOfBirth, gwa, dateHired, currentlyEmployed);
        
        System.out.print("Do you want to add a contact?\n1=YES, input anything for NO: ");
        try {
            string = scanner.getString();
            if("1".equals(string)) {
                HashSet contacts = new HashSet();
                contacts = generateContacts();
                person.setContacts(contacts);
            }
        } catch(Exception e) {

        }
        System.out.print("Do you want to add a role?\n1=YES, input anything for NO: ");
        try {
            string = scanner.getString();
            if("1".equals(string)) {
                HashSet roles = new HashSet();
                roles = generateRoles(roleService);
                person.setRoles(roles);
            }
        } catch(Exception e) {

        }
    	return person;
    }

    public Integer generateId() {
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

    public Name generateName() {
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

    public Address generateAddress() {
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

    public GregorianCalendar generateDateOfBirth() {
    	System.out.println("\n<--- Date of Birth --->\n");
        System.out.print("Input year: ");
        int year = scanner.getInt();
        System.out.print("Input month: "); 
        int month = scanner.getInt();
        System.out.print("Input date of month: ");
        int dayOfMonth = scanner.getInt();
        GregorianCalendar dateOfBirth = new GregorianCalendar(year, month, dayOfMonth);
        return dateOfBirth;
    }

    public GregorianCalendar generateDateHired() {
    	System.out.println("\n<--- Date Hired --->\n");
        System.out.print("Input year: ");
        int year = scanner.getInt();
        System.out.print("Input month: "); 
        int month = scanner.getInt();
        System.out.print("Input date of month: ");
        int dayOfMonth = scanner.getInt();
        GregorianCalendar dateHired = new GregorianCalendar(year, month, dayOfMonth);
        return dateHired;
    }

    public float generateGwa() {
    	System.out.println("\n<--- GWA --->\n");
        System.out.print("GWA: ");
        float gwa = scanner.getFloat();
        return gwa;
    }

    public boolean generateCurrentlyEmployed() {
    	System.out.println("\n<--- Currently Employed --->\n");
        System.out.print("Currently Employed: ");
        boolean currentlyEmployed = scanner.getBoolean();
        return currentlyEmployed;
    }

    public Contact generateContact() {
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

    public HashSet generateContacts() {
        HashSet set = new HashSet();
        Contact contact;
        String string;
        boolean done = false;
        do {
            contact = generateContact();
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

    public Role generateRole() {
        Role role = new Role();
        String string;
        System.out.print("Input: ");
        string = scanner.getString();
        role.setRole(string);
        return role;
    }

    public HashSet generateRoles() {
		HashSet set = new HashSet();
		Role role;
        String string;
        boolean done = false;
		do {
			role = generateRole();
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

    public Role generateRole(RoleService roleService) {
        Role role;
        System.out.println("Available Roles:");
        roleService.read();
        System.out.print("Input ID of Role you want to assign:");
        Integer roleId = generateId();
        role = roleService.getObjectWithId(roleId);
        return role;
    }

    public HashSet generateRoles(RoleService roleService) {
        HashSet roles = new HashSet();
        Role role;
        String choice;
        boolean done = false;
        do {
            role = generateRole(roleService);
            if(role==null) {
                System.out.println("Role does not exist.");
            } else {
                roles.add(role);
            }
            System.out.println("Do you want to add more?");
            System.out.print("1=YES, Input anything for NO: "); 
            choice = scanner.getString();
            if(!"1".equals(choice)) {
                done = true;
            } 
        } while(done==false);
        return roles;
    }
}
