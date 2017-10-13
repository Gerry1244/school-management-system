package com.libertymutual.goforcode.schoolmanagementsystem.models;

public class TeacherDto {
	
	private Teacher teacher;
	
	public TeacherDto(Teacher teacher) {
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
	
	public String getRoleName() {
		return teacher.getRoleName();
	}
	
	public int getGradeLevel() {
		return teacher.getGradeLevel();
	}

}
