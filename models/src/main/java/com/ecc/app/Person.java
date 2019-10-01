package com.ecc.app;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	private int id;
	private Name name;
	private Address address;
	private Date dateOfBirth;
	private Double gwa;
	private Date dateHired;
	private Boolean currentlyEmployed;
	private List<ContactInfo> contactInfo;
	private List<Role> role;

	public Person(Name name, Address address, Date dateOfBirth, Double gwa, 
   	Date dateHired, Boolean currentlyEmployed, ContactInfo contactInfo, Role role) {
	}

	public Person() {

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

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public Double getGWA() {
		return this.gwa;
	}

	public Boolean isCurrentlyEmployed() {
		return this.currentlyEmployed;
	}

	public List<ContactInfo> getContactInfo() {
		return this.contactInfo;
	}

	public List<Role> getRole() {
		return this.role;
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

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGWA(Double gwa) {
		this.gwa = gwa;
	}

	public void setCurrentlyEmployed(Boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
