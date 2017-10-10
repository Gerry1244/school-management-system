package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Student extends User {
	
	@ManyToMany(mappedBy = "students")
	List<Assignment> assignments;
	
	@ManyToOne
	Teacher teacher;
	
	
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

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}


}
