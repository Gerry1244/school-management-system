package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/students")
@Api(description="Use this to get all, create, delete, and update students. This controller also handles getting assignments by student id.")
public class StudentApiController {

	private StudentRepository studentRepo;

	public StudentApiController(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

	@ApiOperation(value = "Get a list of all of the assignments by student id.")
	@GetMapping("{id}/assignments")
	public List<Assignment> getAllAssignmentsByStudent(@PathVariable long id) {
		try {
			Student individualStudent = studentRepo.findOne(id);
			List<Assignment> assignmentList = individualStudent.getAssignments();
			return assignmentList;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	@ApiOperation(value = "Get a list of all of the assignments.")
	@GetMapping("")
	public List<Student> getAll() {
		return studentRepo.findAll();
	}
	
	@ApiOperation(value = "Create a new student.")
	@PostMapping("")
	public Student create(@RequestBody Student student) {
		return studentRepo.save(student);
	}
	
	@ApiOperation(value = "Delete a student.")
	@DeleteMapping("{id}")
	public Student delete(@PathVariable long id) {
		try {
			Student student = studentRepo.findOne(id);
			studentRepo.delete(id);
			return student;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@ApiOperation(value = "Update a student.")
	@PutMapping("{id}")
	public Student update(@RequestBody Student student, @PathVariable long id) {
		student.setId(id);
		return studentRepo.save(student);
	}

}
