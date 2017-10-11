package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Teacher extends User {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "teacher")
	List<Assignment> assignments;
	
	@OneToMany(mappedBy = "teacher")
	List<Student> students;
	

	@Column(nullable = true)
	private int gradeTaught;

	public Teacher() {
	}

	public Teacher(String firstName, String lastName, String email, String password, int gradeTaught, String roleName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gradeTaught = gradeTaught;
		this.roleName = roleName;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
