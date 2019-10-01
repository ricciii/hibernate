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
}