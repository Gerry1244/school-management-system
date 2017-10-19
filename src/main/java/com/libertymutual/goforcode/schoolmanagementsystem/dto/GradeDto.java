package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;

public class GradeDto {

	private Grade grade;
	
	public GradeDto(Grade grade) {
		this.grade = grade;
	}
	
	public Long getGradeId() {
		return this.grade.getId();
	}
	
	public String getletterGradeValue() {
		return grade.getLetterGradeValue();
	}
	
	public String getComment() {
		return grade.getComment();
	}
	
	public Long getGradeAssignmentId() {
		return grade.getAssignment().getId();
	}
	
	public Long getGradeStudentId(){
		return grade.getStudent().getId();
	}
	
	
	
}
