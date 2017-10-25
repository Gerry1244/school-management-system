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
import com.libertymutual.goforcode.schoolmanagementsystem.services.AssignmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Use this to get all, create, delete, and update assignments.")
public class AssignmentApiController {

	private AssignmentService assignmentService;
	
	public AssignmentApiController(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	@ApiOperation(value = "Get a list of all of the assignments.")
	@GetMapping("assignments")
	public List<AssignmentDto> getAll() {
		List<Assignment> assignments = assignmentService.getAll();
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
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
		return new AssignmentDto(assignmentService.getOne(id));
	}
	
	@ApiOperation(value = "Get a list of all of the assignments by student id.")
	@GetMapping("students/{id}/assignments")
	public List<AssignmentDto> getAllAssignmentsByStudent(@PathVariable long id) {
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		List<Assignment> assignments = assignmentService.getAllAssignmentsByStudent(id);
		
		if (assignments != null) {
			for (Assignment assignment : assignments) {
				AssignmentDto assignmentDto = new AssignmentDto(assignment);
				assignmentsDto.add(assignmentDto);
			}
		}
		return assignmentsDto;
	}
	
	@ApiOperation(value = "Get a full list of assignments for a teacher.")
	@GetMapping("teachers/{id}/assignments")
	public List<AssignmentDto> getAllAssigmentsByTeacher(@PathVariable long id) {
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		List<Assignment> assignments = assignmentService.getAllAssignmentsByTeacher(id);
				
		if (assignments != null) {
			for (Assignment assignment : assignments) {
				AssignmentDto assignmentDto = new AssignmentDto(assignment);
				assignmentsDto.add(assignmentDto);
			}
		}
		return assignmentsDto;
	}

	
	@ApiOperation(value = "Creates a new assignment, and associate it to all students under the teacher.")
	@PostMapping({"assignments", "teachers/{id}/assignments"})
	public AssignmentDto createAssignmentAndAssociateToStudents(@RequestBody Assignment assignment, @PathVariable long id) {
			Assignment newAssignment = assignmentService
												.create(assignment.getName(), 
														assignment.getDescription(),
														assignment.getDueDate(), 
														assignment.getComment(), 
														id);			
				return new AssignmentDto(newAssignment);	
	}

	@ApiOperation(value = "Delete an assignment.")
	@DeleteMapping("assignments/{id}")
	public AssignmentDto delete(@PathVariable long id) {
		Assignment assignment = assignmentService.delete(id);	
		return new AssignmentDto(assignment);
	}

	@ApiOperation(value = "Update an assignment.")
	@PutMapping("assignments/{id}")
	public AssignmentDto update(@RequestBody Assignment assignment, @PathVariable long id) {		
		Assignment updatedAssignment = assignmentService
											.update(assignment.getName(), 
													assignment.getDescription(),
													assignment.getDueDate(), 
													assignment.getComment(), 
													id);	
		
		return new AssignmentDto(updatedAssignment);
	}

}
