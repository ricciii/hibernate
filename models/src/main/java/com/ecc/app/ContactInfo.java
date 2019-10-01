package com.ecc.app;

import java.util.ArrayList;

public class ContactInfo {
	private int id;
	private String contactInfo;
	private String type; 

	public enum Type {
		LANDLINE, MOBILE, EMAIL
	}

	public ContactInfo(String type, String contactInfo) {
		this.type = type;
		this.contactInfo = contactInfo;
	}

	public int getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public String getContactInfo() {
		return this.contactInfo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
}