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
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentApiController {
	
	private AssignmentRepository assignmentRepo;
	
	public AssignmentApiController(AssignmentRepository assignmentRepo) {
		this.assignmentRepo = assignmentRepo;
	}
	
	@GetMapping("")
	public List<Assignment> getAll() {
		return assignmentRepo.findAll();
	}
	
	@PostMapping("")
	public Assignment create(@RequestBody Assignment assignment) {
		return assignmentRepo.save(assignment);
	}
	
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

	@PutMapping("{id}")
	public Assignment update(@RequestBody Assignment assignment, @PathVariable long id) {
		assignment.setId(id);
		return assignmentRepo.save(assignment);
	}

}
