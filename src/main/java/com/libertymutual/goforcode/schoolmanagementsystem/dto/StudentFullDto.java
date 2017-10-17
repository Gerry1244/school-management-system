package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;

public class StudentFullDto {

	private Student student;
	
	
	public StudentFullDto(Student student) {
		this.student = student;
	}
	
	
	public Long getUserId() {
		return student.getId();
	}
	
	public String getFirstName() {
		return student.getFirstName();
	}
	
	public String getLastName() {
		return student.getLastName();
	}
	
	public String getEmail() {
		return student.getEmail();
	}
	
	public String getPassword() {
		return student.getPassword();
	}
	
	public String getRoleName() {
		return student.getRoleName();
	}
	
	public int getGradeLevel() {
		return student.getGradeLevel();
	}
	
}
