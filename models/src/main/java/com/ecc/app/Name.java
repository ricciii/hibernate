package com.ecc.app;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="middle_name", nullable=false)
	private String middleName;
	
	@Column(name="suffix", nullable=true)
	private String suffix;

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

	public Name() {

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

	@Override
	public String toString() {
		String string;
		string = lastName + " " + firstName + " " + middleName;
		if(suffix != null) {
			string += ", " + suffix;
		}
		return string;
	}
}