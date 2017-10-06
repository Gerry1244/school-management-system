package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Assignment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=255, nullable=false)
	private String name;
	
	@Column(length=500, nullable=true)
	private String description;
	
	@Column(nullable=true)
	private Date dueDate;
	
	@Column(length=500, nullable=true)
	private String comment;
	
	public Assignment() {}
	
	public Assignment(String name) {
		this.name = name;
	}
	
	public Assignment(String name, String description, Date dueDate, String comment) {
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.comment= comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
