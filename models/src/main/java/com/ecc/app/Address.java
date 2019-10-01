package com.ecc.app;

public class Address {
	private int id;
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

	public int getId() {
		return this.id;
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

	public void setId(int id) {
		this.id = id;
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
}