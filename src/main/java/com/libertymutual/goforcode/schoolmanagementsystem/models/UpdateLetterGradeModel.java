package com.libertymutual.goforcode.schoolmanagementsystem.models;

public class UpdateLetterGradeModel {
	
	private Long id;
	private String letterGradeValue;
	private Long gradeStudentId;
	private Long gradeAssignmentId;
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
	public Long getGradeStudentId() {
		return gradeStudentId;
	}
	public void setGradeStudentId(Long gradeStudentId) {
		this.gradeStudentId = gradeStudentId;
	}
	public Long getGradeAssignmentId() {
		return gradeAssignmentId;
	}
	public void setGradeAssignmentId(Long gradeAssignmentId) {
		this.gradeAssignmentId = gradeAssignmentId;
	}
	

}
