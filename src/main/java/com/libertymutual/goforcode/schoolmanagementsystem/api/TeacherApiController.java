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

import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.models.TeacherDto;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/teachers")
@Api(description = "Use this to get all, create, delete, and update teachers.")
public class TeacherApiController {

	private TeacherRepository teacherRepo;

	public TeacherApiController(TeacherRepository teacherRepo) {
		this.teacherRepo = teacherRepo;
	}
	
	@ApiOperation(value = "Get a specific teacher by id.")
	@GetMapping("{id}")
	public TeacherDto getOne(@PathVariable long id) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			return new TeacherDto(teacher);
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@ApiOperation(value = "Get a list of teachers.")
	@GetMapping("")
	public List<Teacher> getAll() {
		return teacherRepo.findAll();
	}
	

	@ApiOperation(value = "Create a new teacher.")
	@PostMapping("")
	public TeacherDto create(@RequestBody Teacher teacher) {
		teacherRepo.save(teacher);
		return new TeacherDto(teacher);
	}

	@ApiOperation(value = "Delete a teacher.")
	@DeleteMapping("{id}")
	public TeacherDto delete(@PathVariable long id) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			teacherRepo.delete(id);
			return new TeacherDto(teacher);
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@ApiOperation(value = "Update a teacher.")
	@PutMapping("{id}")
	public TeacherDto update(@RequestBody Teacher teacher, @PathVariable long id) {
		teacher.setId(id);
		teacherRepo.save(teacher);
		return new TeacherDto(teacher);
	}

}
