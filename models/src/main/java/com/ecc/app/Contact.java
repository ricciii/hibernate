package com.ecc.app;

import java.util.ArrayList;

public class Contact {
	private int id;
	private String contact;
	private ContactType type; 

	public enum ContactType {
		MOBILE("Mobile"), LANDLINE("Landline"), EMAIL("Email");

		private String string;

		ContactType(String string) {
			this.string = string;
		}

		public String getString() {
			return string;
		}
	}

	public Contact() {
	
	}

	public Contact(ContactType type, String contact) {
		this.type = type;
		this.contact = contact;
	}

	public int getId() {
		return this.id;
	}

	public ContactType getType() {
		return this.type;
	}

	public String getContact() {
		return this.contact;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass())) 
			return false;

		Contact obj2 = (Contact) obj;
		if((this.id == obj2.getId()) && (this.contact.equals(obj2.getContact()))) {
			return true;
		}
		return false;
   	}
   
	public int hashCode() {
		int tmp = 0;
		tmp = (id + contact).hashCode();
		return tmp;
  	}

  	public String toString() {
  		String string;
  		string = "ID: " + id + " Type: " + type + " Contact Info: " + contact + "\n";
  		return string;
  	}
}