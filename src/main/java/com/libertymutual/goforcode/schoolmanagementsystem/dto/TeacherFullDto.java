package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;

public class TeacherFullDto {
	
	private Teacher teacher;
	
	public TeacherFullDto(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public Long getUserId() {
		return teacher.getId();
	}
	
	public String getFirstName() {
		return teacher.getFirstName();
	}
	
	public String getLastName() {
		return teacher.getLastName();
	}
	
	public String getEmail() {
		return teacher.getEmail();
	}
	
	public String getPassword() {
		return teacher.getPassword();
	}
	
	public String getRoleName() {
		return teacher.getRoleName();
	}
	
	public int getGradeLevel() {
		return teacher.getGradeLevel();
	}

}
