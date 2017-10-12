package com.libertymutual.goforcode.schoolmanagementsystem.api;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/grade-level")
@Api(description = "Controller handles gradelevels (e.g. 5th grade)")
public class GradeLevelApiController {
	
	private TeacherRepository teacherRepo;
	
	public GradeLevelApiController(TeacherRepository teacherRepo) {
		this.teacherRepo = teacherRepo;
	}
	

	@ApiOperation(value = "Get a list of teachers by grade level.")
	@GetMapping("{gradeLevel}/teachers")
	public List<Teacher> getAllTeachersByGradeLevel(@PathVariable Integer gradeLevel) {
		List<Teacher> list = null;
		if (gradeLevel != null) {
			list = teacherRepo.findByGradeLevel(gradeLevel);
		}
		return list;

	}

}
