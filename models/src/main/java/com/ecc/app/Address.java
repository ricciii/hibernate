package com.ecc.app;

import java.time.LocalDate;

public class Address {
	//private int id;
	private String street;
	private String barangay;
	private String municipality;
	private String zipCode;

	public Address(String street, String barangay, String municipality, String zipCode) {
		this.street = street;
		this.barangay = barangay;
		this.municipality = municipality;
		this.zipCode = zipCode;
	}

	public Address() {

	}

	public String getStreet() {
		return this.street;
	}

	public String getBarangay() {
		return this.barangay;
	}

	public String getMunicipality() {
		return this.municipality;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String toString() {
		String string;
		string = street + ", " + barangay + ", " + municipality + ", " + zipCode;
		return string;
	}
}