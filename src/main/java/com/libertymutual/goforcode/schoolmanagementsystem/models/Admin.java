package com.libertymutual.goforcode.schoolmanagementsystem.models;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	
	private static final long serialVersionUID = 1L;

	public Admin() {}
	
	public Admin(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

}
