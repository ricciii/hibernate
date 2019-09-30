package com.ecc.app;

public class Address {
	String street;
	String barangay;
	String municipality;
	String zipcode;

	public Address(String street, String barangay, String municipality, String zipcode) {
		this.street = street;
		this.barangay = barangay;
		this.municipality = municipality;
		this.zipcode = zipcode;
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

	public String getZipcode() {
		return this.zipcode;
	}

	public void setStreet() {
		this.street = street;
	}

	public void setBarangay() {
		this.barangay = barangay;
	}

	public void setMunicipality() {
		this.municipality = municipality;
	}

	public void setZipcode() {
		this.zipcode = zipcode;
	}
}