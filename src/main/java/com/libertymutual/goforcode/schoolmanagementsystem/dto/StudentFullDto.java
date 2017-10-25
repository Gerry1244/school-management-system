package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;

public class StudentFullDto {

	private Student student;
	private Long teacherId;
	
	
	public StudentFullDto(Student student, Long teacherId) {
		this.student = student;
		this.teacherId = teacherId;
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


	public Long getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	
}
