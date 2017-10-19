package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Controller handles gradelevels (e.g. 5th grade)")
public class GradeLevelApiController {

	private TeacherRepository teacherRepo;

	public GradeLevelApiController(TeacherRepository teacherRepo) {
		this.teacherRepo = teacherRepo;
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
