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
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

@RestController
@RequestMapping("/api/teachers")
public class TeacherApiController {
	
	private TeacherRepository teacherRepo;

	public TeacherApiController(TeacherRepository teacherRepo) {
		this.teacherRepo = teacherRepo;
	}
	
	@GetMapping("")
	public List<Teacher> getAll() {
		return teacherRepo.findAll();
	}
	
	@PostMapping("")
	public Teacher create(@RequestBody Teacher teacher) {
		return teacherRepo.save(teacher);
	}
	
	@DeleteMapping("{id}")
	public Teacher delete(@PathVariable long id) {
		try {
			Teacher teacher = teacherRepo.findOne(id);
			teacherRepo.delete(id);
			return teacher;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@PutMapping("{id}")
	public Teacher update(@RequestBody Teacher teacher, @PathVariable long id) {
		teacher.setId(id);
		return teacherRepo.save(teacher);
	}

}
