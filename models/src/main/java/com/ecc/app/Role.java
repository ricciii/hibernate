package com.ecc.app;

public class Role {
	private int id;
	private String role;

	public Role() {

	}

	public Role(String role) {
		this.role=role;
	}

	public int getId() {
		return this.id;
	}

	public String getRole() {
		return this.role;
	} 

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass())) 
			return false;

		Role obj2 = (Role) obj;
		if((this.id == obj2.getId()) && (this.role.equals(obj2.getRole()))) {
			return true;
		}
		return false;
   	}
   
	public int hashCode() {
		int tmp = 0;
		tmp = (id + role).hashCode();
		return tmp;
  	}

  	public String toString() {
  		String string;
  		string = "ID: " + id + " Role: " + role + "\n";
  		return string;
  	}
}