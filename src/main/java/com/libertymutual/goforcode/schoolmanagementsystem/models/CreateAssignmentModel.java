package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.sql.Date;

public class CreateAssignmentModel {
	
		 private String name;
		 private String description;
		 private Date dueDate;
		 private String comment;
		 private Long teacherId;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getDueDate() {
			return dueDate;
		}
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public Long getTeacherId() {
			return teacherId;
		}
		public void setTeacherId(Long teacherId) {
			this.teacherId = teacherId;
		}
		 
		 
		
	}

