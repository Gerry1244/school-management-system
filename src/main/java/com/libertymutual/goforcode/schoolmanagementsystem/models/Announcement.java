package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Announcement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=255, nullable=false)
	private String title;
	
	@Column(length=500, nullable=false)
	private String description;
	
	@Column(nullable=true)
	private Date date;
	
	public Announcement() {}
	
	public Announcement(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public Announcement(String title, String description, Date date) {
		this.title = title;
		this.description = description;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

