package com.ecc.app;

import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.*;

@Embeddable
public class Address {
	
	@Column(name="street", nullable=false)
	private String street;
	
	@Column(name="barangay", nullable=false)
	private String barangay;
	
	@Column(name="municipality", nullable=false)
	private String municipality;
	
	@Column(name="zip_code", nullable=false)
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