package com.ecc.app;

public class Person {
	Name name;
	Address address;
	Date dateOfBirth;
	Double gwa;
	Date dateHired;
	Boolean currentlyEmployed;
	ContactInfo contactInfo;

	public Person(Name name, Address address, Date dateOfBirth, 
		Double gwa, Date dateHired, Boolean currentlyEmployed,  ContactInfo contactInfo) {

	}

	public Person() {

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

	public ContactInfo getContactInfo() {
		return this.contactInfo;
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


}
