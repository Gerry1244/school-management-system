package com.libertymutual.goforcode.schoolmanagementsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=2, nullable=true)
	private String letterGradeValue;
	
	@ManyToOne
	Assignment assignment;
	
	@ManyToOne
	Student student;
	
	public Grade() {}
	
	public Grade(String letterGradeValue) {
		this.letterGradeValue = letterGradeValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLetterGradeValue() {
		return letterGradeValue;
	}

	public void setLetterGradeValue(String letterGradeValue) {
		this.letterGradeValue = letterGradeValue;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
