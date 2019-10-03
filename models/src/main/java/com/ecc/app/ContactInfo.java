package com.ecc.app;

import java.util.ArrayList;

public class ContactInfo {
	private int id;
	private String contactInfo;
	private String type; 

	public ContactInfo() {
	
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

	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass())) 
			return false;

		ContactInfo obj2 = (ContactInfo) obj;
		if((this.id == obj2.getId()) && (this.contactInfo.equals(obj2.getContactInfo()))) {
			return true;
		}
		return false;
   	}
   
	public int hashCode() {
		int tmp = 0;
		tmp = (id + contactInfo).hashCode();
		return tmp;
  	}
}