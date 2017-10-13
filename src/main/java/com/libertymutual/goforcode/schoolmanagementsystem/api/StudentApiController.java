package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.ArrayList;
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
import com.libertymutual.goforcode.schoolmanagementsystem.models.StudentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/students")
@Api(description="Use this to get all, create, delete, and update students. This controller also handles getting assignments by student id.")
public class StudentApiController {

	private StudentRepository studentRepo;
	private TeacherRepository teacherRepo;

	public StudentApiController(StudentRepository studentRepo, TeacherRepository teacherRepo) {
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
	}
	
	@ApiOperation(value = "Get a specific student by id.")
	@GetMapping("{id}")
	public StudentDto getOne(@PathVariable long id) {
		try {
			Student student = studentRepo.findOne(id);
			return new StudentDto(student);
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
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
	
	@ApiOperation(value = "Get a list of all of the students.")
	@GetMapping("")
	public List<StudentDto> getAll() {
		List<Student> studentList = studentRepo.findAll();
		
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		for (Student student : studentList) {
			StudentDto studentDto = new StudentDto(student);
			studentDtoList.add(studentDto);
		}
	
		return studentDtoList;
		
	}
	
	@ApiOperation(value = "Create a new student. The ID in the post mapping refers to the teacher being associate with the student.")
	@PostMapping("{id}")
	public StudentDto createAndAssociateTeacher(@RequestBody Student student, @PathVariable long id) {
		Teacher teacher = teacherRepo.findOne(id);
		student.setTeacher(teacher);
		studentRepo.save(student);
		return new StudentDto(student);
	}
	
	@ApiOperation(value = "Delete a student.")
	@DeleteMapping("{id}")
	public StudentDto delete(@PathVariable long id) {
		try {
			Student student = studentRepo.findOne(id);
			studentRepo.delete(id);
			return new StudentDto(student);
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@ApiOperation(value = "Update a student.")
	@PutMapping("{id}")
	public StudentDto update(@RequestBody Student student, @PathVariable long id) {
		student.setId(id);
		studentRepo.save(student);
		return new StudentDto(student);
	}

}
