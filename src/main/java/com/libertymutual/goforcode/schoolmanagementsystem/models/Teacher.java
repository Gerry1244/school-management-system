package com.libertymutual.goforcode.schoolmanagementsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Teacher extends User {
	
	@Column(nullable=false)
	private int gradeTaught;
	
	public Teacher() {}
	
	public Teacher(String firstName, String lastName, String email, String password, int gradeTaught) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gradeTaught = gradeTaught;
	}

	public int getGradeTaught() {
		return gradeTaught;
	}

	public void setGradeTaught(int gradeTaught) {
		this.gradeTaught = gradeTaught;
	}

}
