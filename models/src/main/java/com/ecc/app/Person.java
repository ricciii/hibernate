package com.ecc.app;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Person {
	private int id;
	private Name name;
	private Address address;
	private Calendar dateOfBirth;
	private float gwa;
	private Calendar dateHired;
	private Boolean currentlyEmployed;
	private Set contactInfo;
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

	public Set getContactInfo() {
		return this.contactInfo;
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

	public void setContactInfo(Set contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}
}
