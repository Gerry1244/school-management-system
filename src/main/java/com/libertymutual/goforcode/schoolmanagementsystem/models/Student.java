package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student extends User {
	
	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "students", cascade=CascadeType.ALL)
	List<Assignment> assignments;
	
	@OneToMany(mappedBy="student", cascade=CascadeType.ALL)
	List<Grade> grades;
	
	@JsonIgnore
	@ManyToOne
	Teacher teacher;
	
	
	@Column(nullable=true)
	private int gradeLevel;
	
	public Student() {}
	
	public Student(String firstName, String lastName, String email, String password, int gradeLevel, String roleName) {
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

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
