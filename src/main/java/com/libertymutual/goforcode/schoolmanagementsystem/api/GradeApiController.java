package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.AssignmentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.GradeDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.UpdateLetterGradeModel;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/grades")
@Api(description = "This controller deals with grades.")
public class GradeApiController {

	private GradeRepository gradeRepo;
	private AssignmentRepository assignmentRepo;
	private StudentRepository studentRepo;

	public GradeApiController(GradeRepository gradeRepo, AssignmentRepository assignmentRepo,
			StudentRepository studentRepo) {
		this.gradeRepo = gradeRepo;
		this.assignmentRepo = assignmentRepo;
		this.studentRepo = studentRepo;
	}
	
	@ApiOperation(value = "Get a single grade by id")
	@GetMapping("{id}")
	public GradeDto getOne(@PathVariable long id) {
		Grade grade = gradeRepo.findOne(id);
		if (grade != null) {
			return new GradeDto(grade);
		} else
			System.err.println("Could not find grade with this id");
			return null;
	}

	@ApiOperation(value = "Get a full list of all grades for all assignments and students")
	@GetMapping("")
	public List<GradeDto> getAll() {
		List<Grade> grades;
		List<GradeDto> gradesDto = new ArrayList<GradeDto>();

		grades = gradeRepo.findAll();
		if (grades != null) {
			for (Grade grade : grades) {
				GradeDto gradeDto = new GradeDto(grade);
				gradesDto.add(gradeDto);
			}
			return gradesDto;
		} else
			return null;
	}

	@ApiOperation(value = "Get all the grades for a particular assignment")
	@GetMapping("assignments/{id}")
	public List<GradeDto> getAllGradesForAnAssignment(@PathVariable long id) {
		List<Grade> grades;
		List<GradeDto> gradesDto = new ArrayList<GradeDto>();
		Assignment assignment = assignmentRepo.findOne(id);
		grades = gradeRepo.findByAssignment(assignment);
		if (grades != null) {
			for (Grade grade : grades) {
				GradeDto gradeDto = new GradeDto(grade);
				gradesDto.add(gradeDto);
			}
			return gradesDto;
		} else
			System.err.println("Could not find assignment id in grades table");
		return null;
	}

	@ApiOperation(value = "Get all the grades for a particular assignment")
	@GetMapping("students/{id}")
	public List<GradeDto> getAllGradesForAStudent(@PathVariable long id) {
		List<Grade> grades;
		List<GradeDto> gradesDto = new ArrayList<GradeDto>();
		Student student = studentRepo.findOne(id);
		grades = gradeRepo.findByStudent(student);
		if (grades != null) {
			for (Grade grade : grades) {
				GradeDto gradeDto = new GradeDto(grade);
				gradesDto.add(gradeDto);
			}
			return gradesDto;
		} else
			System.err.println("Could not find student id in grades table");
		return null;
	}

	@ApiOperation(value = "Update a grade. The RequestBody must send letterGradeValue, gradeStudentId, & gradeAssignmentId")
	@PutMapping("{id}")
	public GradeDto update(@RequestBody UpdateLetterGradeModel grade, @PathVariable long id) {
		try {
			Student student = studentRepo.findOne(grade.getGradeStudentId());
			Assignment assignment = assignmentRepo.findOne(grade.getGradeAssignmentId());
			Grade updatedGrade = new Grade();
			updatedGrade.setId(id);
			updatedGrade.setLetterGradeValue(grade.getLetterGradeValue());
			updatedGrade.setAssignment(assignment);
			updatedGrade.setStudent(student);
			gradeRepo.save(updatedGrade);
			return new GradeDto(updatedGrade);
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Grade id: " + id + " not found. Error: " + erdae);
			return null;
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Grade in request body was not valid: " + dive);
			return null;
		}
	}

}
