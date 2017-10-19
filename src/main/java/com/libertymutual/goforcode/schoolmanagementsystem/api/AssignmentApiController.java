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
@RequestMapping("/api")
@Api(description = "Use this to get all, create, delete, and update assignments.")
public class AssignmentApiController {

	private AssignmentRepository assignmentRepo;
	private StudentRepository studentRepo;
	private TeacherRepository teacherRepo;
	private GradeRepository gradeRepo;

	public AssignmentApiController(AssignmentRepository assignmentRepo, StudentRepository studentRepo,
								 TeacherRepository teacherRepo, GradeRepository gradeRepo) {
		this.assignmentRepo = assignmentRepo;
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
		this.gradeRepo = gradeRepo;

	}

	@ApiOperation(value = "Get a list of all of the assignments.")
	@GetMapping("assignments")
	public List<AssignmentDto> getAll() {
		List<Assignment> assignments;
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		assignments = assignmentRepo.findAll();
		if (assignments != null) {
			for (Assignment assignment : assignments) {
				AssignmentDto assignmentDto = new AssignmentDto(assignment);
				assignmentsDto.add(assignmentDto);
			}
			return assignmentsDto;
		} else
			return null;
	}

	@ApiOperation(value = "Get a specific assignment by id.")
	@GetMapping("assignments/{id}")
	public AssignmentDto getOne(@PathVariable long id) {
		try {
			Assignment assignment = assignmentRepo.findOne(id);
			return new AssignmentDto(assignment);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignment id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}
	
	@ApiOperation(value = "Get a list of all of the assignments by student id.")
	@GetMapping("students/{id}/assignments")
	public List<AssignmentDto> getAllAssignmentsByStudent(@PathVariable long id) {
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		try {
			Student student = studentRepo.findOne(id);
			if (student != null) {
				List<Grade> grades = student.getGrades();
				for (Grade g : grades) {
					AssignmentDto assignmentDto = new AssignmentDto(g.getAssignment());
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
	
	@ApiOperation(value = "Get a full list of assignments for a teacher.")
	@GetMapping("teachers/{id}/assignments")
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

	
	@ApiOperation(value = "Creates a new assignment, and associate it to all students under the teacher.")
	@PostMapping({"assignments", "teachers/{id}/assignments"})
	public AssignmentDto createAssignmentAndAssociateToStudents(@RequestBody Assignment assignment, @PathVariable long id) {
		List<Student> students;
		Teacher teacher;
		
		
		try {
			teacher = teacherRepo.findOne(id);
			students = studentRepo.findByTeacher(teacher);
			
			Assignment newAssignment = new Assignment(assignment.getName(), assignment.getDescription(),
					assignment.getDueDate(), assignment.getComment(), teacher);
			
			if (teacher != null && students != null) {
				newAssignment.setStudents(students);
				assignmentRepo.save(newAssignment);
				for (Student student : students) {
					Grade grade = new Grade();
					grade.setAssignment(newAssignment);
					grade.setStudent(student);
					grade.setLetterGradeValue("Not graded.");	
					gradeRepo.save(grade);
				}
			
				
				return new AssignmentDto(newAssignment);
			} else
				return null;
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Assignment in request body was not valid: " + dive);
			return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("createAndAssociateToStudents failed:" + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Delete an assignment.")
	@DeleteMapping("assignments/{id}")
	public AssignmentDto delete(@PathVariable long id) {
		try {
			Assignment assignment = assignmentRepo.findOne(id);
			for (Grade g : assignment.getGrades()) {
				g.setAssignment(null);
				gradeRepo.save(g);
			}
			assignmentRepo.delete(id);
			return new AssignmentDto(assignment);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignment id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	@ApiOperation(value = "Update an assignment.")
	@PutMapping("assignments/{id}")
	public AssignmentDto update(@RequestBody Assignment assignment, @PathVariable long id) {
		try {
			Teacher teacher = assignmentRepo.findOne(id).getTeacher();
			assignment.setId(id);
			assignment.setTeacher(teacher);
			assignmentRepo.save(assignment);
			return new AssignmentDto(assignment);
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Assignment in request body was not valid: " + dive);
			return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignment id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

}
