package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property="id")
@Entity
public class Assignment {
	
	@ManyToMany
	List<Student> students;
	
	@ManyToOne
	Teacher teacher;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="assignment", cascade=CascadeType.ALL)
	List<Grade> grades;
	
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
	
	public Assignment(String name, String description, Date dueDate, String comment, Teacher teacher) {
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.comment= comment;
		this.teacher = teacher;
	}
	
	public Assignment(String name, String description, String comment, Teacher teacher) {
		this.name = name;
		this.description = description;
		this.comment= comment;
		this.teacher = teacher;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
