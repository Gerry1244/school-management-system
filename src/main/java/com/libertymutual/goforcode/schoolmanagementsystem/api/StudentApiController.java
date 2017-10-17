package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.AssignmentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.StudentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/students")
@Api(description = "Use this to get all, create, delete, and update students. This controller also handles getting assignments by student id.")
public class StudentApiController {

	private StudentRepository studentRepo;
	private TeacherRepository teacherRepo;
	private GradeRepository gradeRepo;
	private AssignmentRepository assignmentRepo;

	public StudentApiController(StudentRepository studentRepo, TeacherRepository teacherRepo, GradeRepository gradeRepo, AssignmentRepository assignmentRepo) {
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
		this.gradeRepo = gradeRepo;
		this.assignmentRepo = assignmentRepo;
	}

	@ApiOperation(value = "Get a specific student by id.")
	@GetMapping("{id}")
	public StudentDto getOne(@PathVariable long id) {
		try {
			Student student = studentRepo.findOne(id);
			return new StudentDto(student);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Student id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Get a list of all of the assignments by student id.")
	@GetMapping("{id}/assignments")
	public List<AssignmentDto> getAllAssignmentsByStudent(@PathVariable long id) {
		List<Assignment> assignments;
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		try {
			Student student = studentRepo.findOne(id);
			if (student != null) {
				assignments = student.getAssignments();
				for (Assignment assignment : assignments) {
					AssignmentDto assignmentDto = new AssignmentDto(assignment);
					assignmentsDto.add(assignmentDto);
				}
				return assignmentsDto;
			}
			return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Student id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Get a list of all of the students.")
	@GetMapping("")
	public List<StudentDto> getAll() {
		List<Student> students;
		List<StudentDto> studentsDto = new ArrayList<StudentDto>();
		students = studentRepo.findAll();
		if (students != null) {
			for (Student student : students) {
				StudentDto studentDto = new StudentDto(student);
				studentsDto.add(studentDto);
			}
			return studentsDto;
		}
		return null;
	}

	@ApiOperation(value = "Create a new student. The ID in the post mapping refers to the teacher being associate with the student.")
	@PostMapping("{id}")
	public StudentDto createAndAssociateTeacher(@RequestBody Student student, @PathVariable long id) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			if (teacher != null) {
				student.setTeacher(teacher);
				studentRepo.save(student);
				return new StudentDto(student);
			} else {
				System.err.println("Teacher id: " + id + " not found");
				return null;
			}
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Student in request body was not valid: " + dive);
			return null;
		}
	}
	
	@ApiOperation(value = "Associate an existing student to a teacher.")
	@PutMapping("{id}/teachers/{teacherId}")
	public StudentDto associateAnExistingStudentToTeacher(@RequestBody Student student, @PathVariable long id, @PathVariable long teacherId) {
		try {
			Teacher teacher = teacherRepo.findOne(teacherId);
			if (teacher != null) {
				student.setTeacher(teacher);
				student.setId(id);
				studentRepo.save(student);
				return new StudentDto(student);
			} else {
				System.err.println("Teacher id: " + id + " not found");
				return null;
			}
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Student in request body was not valid: " + dive);
			return null;
		}
	}

	@ApiOperation(value = "Delete a student.")
	@DeleteMapping("{id}")
	public StudentDto delete(@PathVariable long id) {
		try {
			Student student = studentRepo.findOne(id);
			List<Assignment> assignments = student.getAssignments();
			for (Assignment a : assignments) {
				a.getStudents().remove(student);
				assignmentRepo.save(a);
			}
			for (Grade g : student.getGrades()) {
				g.setStudent(null);
				gradeRepo.save(g);
			}
			studentRepo.delete(id);
			return new StudentDto(student);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Student id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Update a student.")
	@PutMapping("{id}")
	public StudentDto update(@RequestBody Student student, @PathVariable long id) {
		try {
			student.setId(id);
			studentRepo.save(student);
			return new StudentDto(student);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Student id: " + id + " not found. Error: " + erdae);
			return null;
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Student in request body was not valid: " + dive);
			return null;
		}
	}
	
	

}
