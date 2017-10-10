package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student extends User {
	
	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "students")
	List<Assignment> assignments;
	
	@ManyToMany
	List<Grade> grades;
	
	@ManyToOne
	Teacher teacher;
	
	
	@Column(nullable=true)
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
