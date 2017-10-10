package com.libertymutual.goforcode.schoolmanagementsystem.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/grades")
@Api(description = "This controller deals with grades.")
public class GradeApiController {

	private GradeRepository gradeRepo;

	public GradeApiController(GradeRepository gradeRepo) {
		this.gradeRepo = gradeRepo;
	}
	
	@ApiOperation(value = "Update a grade.")
	@PutMapping("{id}")
	public Grade update(@RequestBody Grade grade, @PathVariable long id) {
		grade.setId(id);
		return gradeRepo.save(grade);
	}

}
