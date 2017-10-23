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
import com.libertymutual.goforcode.schoolmanagementsystem.services.EmailApiService;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Use this to get all, create, delete, and update teachers.")
public class TeacherApiController {

	private TeacherRepository teacherRepo;
	private AssignmentRepository assignmentRepo;
	private StudentRepository studentRepo;
	private PasswordEncoder encoder;
	private UserRepository userRepo;
	private EmailApiService emailService;

	public TeacherApiController(TeacherRepository teacherRepo, AssignmentRepository assignmentRepo,
			StudentRepository studentRepo, PasswordEncoder encoder, UserRepository userRepo,
			EmailApiService emailService) {
		this.teacherRepo = teacherRepo;
		this.assignmentRepo = assignmentRepo;
		this.studentRepo = studentRepo;
		this.encoder = encoder;
		this.userRepo = userRepo;
		this.emailService = emailService;
	}

	@ApiOperation(value = "Get a specific teacher by id including password.")
	@GetMapping("teachers/{id}")
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
	@GetMapping("teachers")
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

	@ApiOperation(value = "Create a new teacher and send confirmation email to teacher email address.")
	@PostMapping("teachers")
	public TeacherDto create(@RequestBody Teacher teacher, HttpServletResponse response) {
		try {
			User existingTeacher = userRepo.findByEmail(teacher.getEmail());
			if (existingTeacher == null && teacher.getRoleName().equals("TEACHER")) {
				teacher.setPassword(encoder.encode(teacher.getPassword()));
				teacherRepo.save(teacher);

				try {
					emailService.sendSimpleMessage(teacher.getEmail());

				} catch (UnirestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
	@DeleteMapping("teachers/{id}")
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
	@PutMapping("teachers/{id}")
	public TeacherDto update(@RequestBody Teacher teacher, @PathVariable long id) {
		String submittedPassword = teacher.getPassword();
		String databasePassword = teacherRepo.findOne(id).getPassword();
		if (!submittedPassword.equals(databasePassword)) {
			String encodedPassword = encoder.encode(submittedPassword);
			teacher.setPassword(encodedPassword);
		}
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

	@ApiOperation(value = "Get a list of teachers by grade level.")
	@GetMapping("grade-level/{gradeLevel}/teachers")
	public List<TeacherDto> getAllTeachersByGradeLevel(@PathVariable Integer gradeLevel) {
		List<Teacher> teachers;
		List<TeacherDto> teachersDto = new ArrayList<TeacherDto>();
		try {
			teachers = teacherRepo.findByGradeLevel(gradeLevel);
			if (teachers != null) {
				for (Teacher teacher : teachers) {
					TeacherDto teacherDto = new TeacherDto(teacher);
					teachersDto.add(teacherDto);
				}
				return teachersDto;
			}
			return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Grade level " + gradeLevel + " not found. Error: " + erdae);
			return null;
		}

	}
}
