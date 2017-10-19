package com.libertymutual.goforcode.schoolmanagementsystem.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/api")
@Api(description = "Controller handles gradelevels (e.g. 5th grade)")
public class GradeLevelApiController {

	private TeacherRepository teacherRepo;

	public GradeLevelApiController(TeacherRepository teacherRepo) {
		this.teacherRepo = teacherRepo;
	}

	
}
