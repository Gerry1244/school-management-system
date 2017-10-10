package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Teacher extends User {

	@OneToMany(mappedBy = "teacher")
	List<Assignment> assignments;
	
	@OneToMany(mappedBy = "teacher")
	List<Student> students;
	

	@Column(nullable = false)
	private int gradeTaught;

	public Teacher() {
	}

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

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}


}
