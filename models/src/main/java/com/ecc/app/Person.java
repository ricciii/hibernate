package com.ecc.app;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Person implements Comparable<Person> {
	private int id;
	private Name name;
	private Address address;
	private Calendar dateOfBirth;
	private float gwa;
	private Calendar dateHired;
	private Boolean currentlyEmployed;
	private Set contacts;
	private Set roles;

	public Person() {

	}

	public Person(Name name, Address address, Calendar dateOfBirth, float gwa, 
   	Calendar dateHired, Boolean currentlyEmployed) {
   		this.name = name;
   		this.address = address;
   		this.dateOfBirth = dateOfBirth;
   		this.gwa = gwa;
   		this.dateHired = dateHired;
   		this.currentlyEmployed = currentlyEmployed;
	}

	public int getId() {
		return this.id;
	}

	public Name getName() {
		return this.name;
	}

	public Address getAddress() {
		return this.address;
	}

	public Calendar getDateOfBirth() {
		return this.dateOfBirth;
	}

	public float getGwa() {
		return this.gwa;
	}

	public Calendar getDateHired() {
		return this.dateHired;
	}

	public Boolean getCurrentlyEmployed() {
		return this.currentlyEmployed;
	}

	public Set getContacts() {
		return this.contacts;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGwa(float gwa) {
		this.gwa = gwa;
	}

	public void setDateHired(Calendar dateHired) {
		this.dateHired = dateHired;
	}

	public void setCurrentlyEmployed(Boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}

	public void setContacts(Set contacts) {
		this.contacts = contacts;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		String string;
		string = "ID: " + id + "\n"; 
		string += "Name: " + name + "\n";
		string += "Address: "+ address + "\n";
		string += "Date Of Birth: " + dateOfBirth.get(Calendar.YEAR) + "-" + dateOfBirth.get(Calendar.MONTH) + "-" + dateOfBirth.get(Calendar.DATE) + "\n";
		string += "GWA: " + gwa + "\n";
		string += "Date Hired: " + dateHired.get(Calendar.YEAR) + "-" + dateHired.get(Calendar.MONTH) + "-" + dateHired.get(Calendar.DATE) + "\n";
		string += "Currently Employed: " + currentlyEmployed + "\n";
		string += "Contact Information: \n";
        for(Object contact: contacts) {
    		string += "--> " + contact;
    	}
		string += "Role Information: \n";
        for(Object role: roles) {
    		string += "--> " + role;
    	}
    	return string;
	}

	@Override
	public int compareTo(Person person) {
		return Float.compare(gwa, person.getGwa());
	}
}
