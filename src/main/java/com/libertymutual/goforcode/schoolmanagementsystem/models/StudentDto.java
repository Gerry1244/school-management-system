package com.libertymutual.goforcode.schoolmanagementsystem.models;

public class StudentDto {

	private Student student;
	
	
	public StudentDto(Student student) {
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
	
	public String getRoleName() {
		return student.getRoleName();
	}
	
	public int getGradeLevel() {
		return student.getGradeLevel();
	}
	
}
