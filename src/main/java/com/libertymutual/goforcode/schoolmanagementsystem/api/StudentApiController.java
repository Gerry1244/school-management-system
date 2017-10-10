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

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

	private StudentRepository studentRepo;

	public StudentApiController(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

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
	
	@GetMapping("")
	public List<Student> getAll() {
		return studentRepo.findAll();
	}
	
	@PostMapping("")
	public Student create(@RequestBody Student student) {
		return studentRepo.save(student);
	}
	
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

	@PutMapping("{id}")
	public Student update(@RequestBody Student student, @PathVariable long id) {
		student.setId(id);
		return studentRepo.save(student);
	}

}
