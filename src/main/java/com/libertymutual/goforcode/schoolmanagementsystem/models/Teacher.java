package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Teacher extends User {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "teacher", cascade=CascadeType.ALL)
	List<Assignment> assignments;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "teacher", cascade=CascadeType.ALL)
	List<Student> students;
	

	@Column(nullable = true)
	private int gradeLevel;

	public Teacher() {
	}

	public Teacher(String firstName, String lastName, String email, String password, int gradeLevel, String roleName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gradeLevel = gradeLevel;
		this.roleName = roleName;
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
