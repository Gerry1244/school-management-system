package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Admin extends User {
	
	public Admin() {}
	
	public Admin(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

}
