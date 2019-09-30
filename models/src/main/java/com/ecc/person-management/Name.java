package com.ecc.person_management.models;

public class Name {
	String lastName;
	String firstName;
	String middleName;
	String suffix;

	public Name(String lastName, String firstName, String middleName, String suffix) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffix = suffix;
	}

	public Name(String lastName, String firstName, String middleName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}