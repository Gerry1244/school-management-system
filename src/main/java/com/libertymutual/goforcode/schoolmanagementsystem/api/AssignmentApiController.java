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
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/assignments")
@Api(description="Use this to get all, create, delete, and update assignments.")
public class AssignmentApiController {
	
	private AssignmentRepository assignmentRepo;
	private StudentRepository studentRepo;
	
	public AssignmentApiController(AssignmentRepository assignmentRepo, StudentRepository studentRepo) {
		this.assignmentRepo = assignmentRepo;
		this.studentRepo = studentRepo;
		assignmentRepo.save(new Assignment("Essay on some dumb topic"));
		//seed data to test a teacher creating a new assignment and associating all students under that teacher
		
	}
	
	@ApiOperation(value = "Get a list of all of the assignments.")
	@GetMapping("")
	public List<Assignment> getAll() {
		return assignmentRepo.findAll();
	}
	
	@ApiOperation(value = "Create a new assignment, and associate it to all students under the teacher.")
	@PostMapping("")
	public Assignment create(@RequestBody Assignment assignment, @RequestBody Teacher teacher) {
		// find all students associated with teacher
		List<Student> students;
		try {
			students = studentRepo.findByTeacher(teacher);
			assignment.setStudents(students);
			return assignmentRepo.save(assignment);
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}

	}
	
	@ApiOperation(value = "Delete an assignment.")
	@DeleteMapping("{id}")
	public Assignment delete(@PathVariable long id) {
		try {
			Assignment assignment = assignmentRepo.findOne(id);
			assignmentRepo.delete(id);
			return assignment;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@ApiOperation(value = "Update an assignment.")
	@PutMapping("{id}")
	public Assignment update(@RequestBody Assignment assignment, @PathVariable long id) {
		assignment.setId(id);
		return assignmentRepo.save(assignment);
	}
	
	@ApiOperation(value = "Get a list of students assigned to a particular assignment.")
	@GetMapping("{id}")
	public List<Student> getAllStudentsByAssignment(@PathVariable long id) {
		try {
			Assignment individualAssignment = assignmentRepo.findOne(id);
			List<Student> studentList = individualAssignment.getStudents();
			return studentList;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

}
