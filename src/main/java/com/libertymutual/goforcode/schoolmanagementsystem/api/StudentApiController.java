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

import com.libertymutual.goforcode.schoolmanagementsystem.dto.StudentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.StudentFullDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api") 
@Api(description = "Use this to get all, create, delete, and update students. This controller also handles getting assignments by student id.")
public class StudentApiController {

	private StudentRepository studentRepo;
	private TeacherRepository teacherRepo;
	private GradeRepository gradeRepo;
	private AssignmentRepository assignmentRepo;
	private PasswordEncoder encoder;
	private UserRepository userRepo;

	public StudentApiController(StudentRepository studentRepo, TeacherRepository teacherRepo, GradeRepository gradeRepo,
			AssignmentRepository assignmentRepo, PasswordEncoder encoder, UserRepository userRepo) {
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
		this.gradeRepo = gradeRepo;
		this.assignmentRepo = assignmentRepo;
		this.encoder = encoder;
		this.userRepo = userRepo;
	}

	@ApiOperation(value = "Get a specific student by id including password.")
	@GetMapping("students/{id}")
	public StudentFullDto getOne(@PathVariable long id) {
		try {
			Student student = studentRepo.findOne(id);
			return new StudentFullDto(student);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Student id: " + id + " not found. Error: " + erdae);
			return null; 
		}
	}
	
	@ApiOperation(value = "Create a new student. The ID in the post mapping refers to the teacher being associate with the student.")
	@PostMapping({"students/{id}", "teachers/{id}/students"})
	public StudentDto createAndAssociateTeacher(@RequestBody Student student, @PathVariable long id, HttpServletResponse response) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			User existingStudent = userRepo.findByEmail(student.getEmail());
			if (teacher != null && existingStudent == null && student.getRoleName().equals("STUDENT")) {
				student.setPassword(encoder.encode(student.getPassword()));
				student.setTeacher(teacher);
				studentRepo.save(student);
				return new StudentDto(student);
			} else if (existingStudent != null) {
				System.err.println("Student already exists with the the email: " + student.getEmail());
				response.setStatus(400);
				return null; 
			}		
			else
				return null;
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Student in request body was not valid: " + dive);
			return null;  
		}
	}
	
	@ApiOperation(value = "Delete a student.")
	@DeleteMapping("students/{id}")
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

	

	@ApiOperation(value = "Get a list of all of the students.")
	@GetMapping("students")
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

	
	@ApiOperation(value = "Associate an existing student to a teacher.")
	@PutMapping({"students/{id}/teachers/{teacherId}", "teacher/{teacherId}/student/{id}/teachers"})
	public StudentDto associateAnExistingStudentToTeacher(@RequestBody Student student, @PathVariable long id,
			@PathVariable long teacherId) {
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
  
	

	@ApiOperation(value = "Update a student.")
	@PutMapping("students/{id}")
	public StudentDto update(@RequestBody Student student, @PathVariable long id) {
		String submittedPassword = student.getPassword();
		String databasePassword = studentRepo.findOne(id).getPassword();
		if (!submittedPassword.equals(databasePassword)) {
			String encodedPassword = encoder.encode(submittedPassword);
			student.setPassword(encodedPassword);
		}
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
	
	@ApiOperation(value = "Get a list of students assigned to a particular assignment, by assignment Id.")
	@GetMapping("assignments/{id}/students")
	public List<StudentDto> getAllStudentsByAssignment(@PathVariable long id) {
		List<Student> students;
		List<StudentDto> studentsDto = new ArrayList<StudentDto>();
		try {
			Assignment assignment = assignmentRepo.findOne(id);
			if (assignment != null) {
				students = assignment.getStudents();
				for (Student student : students) {
					StudentDto studentDto = new StudentDto(student);
					studentsDto.add(studentDto);
				}
				return studentsDto;
			} else
				return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignment id: " + id + " not found. Error: " + erdae);
			return null;
		} catch (Exception e) {
			System.err.println("Student getAllStudentsByAssignment() failed: " + e.getClass().getName());
			return null;
		}
	}

}
