package com.libertymutual.goforcode.schoolmanagementsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Student extends User {
	
	@Column(nullable=false)
	private int gradeLevel;
	
	public Student() {}
	
	public Student(String firstName, String lastName, String email, String password, int gradeLevel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gradeLevel = gradeLevel;
	}

	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

}
