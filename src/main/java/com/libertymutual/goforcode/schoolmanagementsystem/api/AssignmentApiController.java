package com.libertymutual.goforcode.schoolmanagementsystem.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentApiController {
	
	private AssignmentRepository assignmentRepo;

	public AssignmentApiController(AssignmentRepository assignmentRepo) {
		this.assignmentRepo = assignmentRepo;
	}

}
