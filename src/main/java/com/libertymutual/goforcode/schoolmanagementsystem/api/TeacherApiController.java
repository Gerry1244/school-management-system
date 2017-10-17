package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.AssignmentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherFullDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/teachers")
@Api(description = "Use this to get all, create, delete, and update teachers.")
public class TeacherApiController {

	private TeacherRepository teacherRepo;
	private AssignmentRepository assignmentRepo;
	private StudentRepository studentRepo;
	private PasswordEncoder encoder;
	private UserRepository userRepo;

	public TeacherApiController(TeacherRepository teacherRepo, AssignmentRepository assignmentRepo,
			StudentRepository studentRepo, PasswordEncoder encoder, UserRepository userRepo) {
		this.teacherRepo = teacherRepo;
		this.assignmentRepo = assignmentRepo;
		this.studentRepo = studentRepo;
		this.encoder = encoder;
	}

	@ApiOperation(value = "Get a specific teacher by id including password.")
	@GetMapping("{id}")
	public TeacherFullDto getOne(@PathVariable long id) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			return new TeacherFullDto(teacher);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Teacher id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Get a list of teachers.")
	@GetMapping("")
	public List<TeacherDto> getAll() {
		List<Teacher> teachers;
		List<TeacherDto> teachersDto = new ArrayList<TeacherDto>();
		teachers = teacherRepo.findAll();
		if (teachers != null) {
			for (Teacher teacher : teachers) {
				TeacherDto teacherDto = new TeacherDto(teacher);
				teachersDto.add(teacherDto);
			}
			return teachersDto;
		} else
			return null;
	}

	@ApiOperation(value = "Create a new teacher.")
	@PostMapping("")
	public TeacherDto create(@RequestBody Teacher teacher, HttpServletResponse response) {
		try {
			User existingTeacher = userRepo.findByEmail(teacher.getEmail());
			if (existingTeacher == null && teacher.getRoleName().equals("TEACHER")) {
				teacher.setPassword(encoder.encode(teacher.getPassword()));
				teacherRepo.save(teacher);
				return new TeacherDto(teacher);
			}
			System.err.println("Teacher already exists with the the email: " + teacher.getEmail());
			response.setStatus(400);
			return null;

		} catch (DataIntegrityViolationException dive) {
			System.err.println("Teacher in request body was not valid: " + dive);
			return null;
		}
	}

	@ApiOperation(value = "Delete a teacher.")
	@DeleteMapping("{id}")
	public TeacherDto delete(@PathVariable long id) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			List<Assignment> assignments = teacher.getAssignments();
			List<Student> students = studentRepo.findByTeacher(teacher);
			for (Assignment a : assignments) {
				a.setTeacher(null);
				assignmentRepo.save(a);
			}
			for (Student s : students) {
				s.setTeacher(null);
				studentRepo.save(s);
			}
			teacherRepo.delete(id);
			return new TeacherDto(teacher);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Teacher id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Update a teacher.")
	@PutMapping("{id}")
	public TeacherDto update(@RequestBody Teacher teacher, @PathVariable long id) {
		try {
			teacher.setId(id);
			teacherRepo.save(teacher);
			return new TeacherDto(teacher);
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Teacher in request body was not valid: " + dive);
			return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Teaher id: " + id + " not found. Error: " + erdae);
			return null;
		}

	}

	@ApiOperation(value = "Get a full list of assignments for a teacher.")
	@GetMapping("{id}/assignments")
	public List<AssignmentDto> getAllAssigmentsByTeacher(@PathVariable long id) {
		List<Assignment> assignments;
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		try {
			Teacher teacher = teacherRepo.findOne(id);
			assignments = assignmentRepo.findByTeacher(teacher);
			if (assignments != null) {
				for (Assignment assignment : assignments) {
					AssignmentDto assignmentDto = new AssignmentDto(assignment);
					assignmentsDto.add(assignmentDto);
				}
			}
			return assignmentsDto;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Teaher id: " + id + " not found. Error: " + erdae);
			return null;
		}

	}
}
