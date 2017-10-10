package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=2, nullable=true)
	private String letterGradeValue;
	
	@ManyToMany(mappedBy="grades")
	List<Assignment> assignments;
	
	@ManyToMany(mappedBy="grades")
	List<Student> students;
	
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

}
