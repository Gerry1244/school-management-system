package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Date;

public class AssignmentDto {

	private Assignment assignment;

	public AssignmentDto(Assignment assignment) {
		this.assignment = assignment;
	}

	public Long getAssignmentId() {
		return assignment.getId();
	}

	public String getAssignmentName() {
		return assignment.getName();
	}

	public String getAssignmentDescription() {
		return assignment.getDescription();

	}

	public Date getAssignmentDueDate() {
		return assignment.getDueDate();
	}

	public String getAssignmentComment() {
		return assignment.getComment();

	}

}
