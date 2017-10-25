package com.libertymutual.goforcode.schoolmanagementsystem.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherFullDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.services.EmailApiService;

import org.springframework.security.crypto.password.PasswordEncoder;

public class TeacherApiTests { 
	private TeacherRepository teacherRepo;
	private TeacherApiController controller;
	private AssignmentRepository assignmentRepository;
	private StudentRepository studentRepository;
	private PasswordEncoder encoder;
	private UserRepository userRepo;
	private EmailApiService emailService;
	private long id;

	@Before
	public void setUp() {
		userRepo = mock(UserRepository.class);
		teacherRepo = mock(TeacherRepository.class);
		assignmentRepository = mock(AssignmentRepository.class);
		studentRepository = mock(StudentRepository.class);
		encoder = mock(PasswordEncoder.class);
		emailService = mock(EmailApiService.class);
		controller = new TeacherApiController(teacherRepo, assignmentRepository, studentRepository, encoder, userRepo, emailService);
	}
	
	@Test
	public void test_getAll_returns_all_teachers_returned_by_the_repo() {
		//arrange
		ArrayList<Teacher> teachers = new ArrayList<Teacher>(); 
		teachers.add(new Teacher()); 
		teachers.add(new Teacher()); 
		
		when(teacherRepo.findAll()).thenReturn(teachers); 
		
		//act
		List<TeacherDto> actual = controller.getAll(); 
		
		//assert
		assertThat(actual.size()).isEqualTo(2); 
		assertThat(actual.get(0)).isSameAs(teachers.get(0)); 
		verify(teacherRepo).findAll(); 		
		
	}
	
	@Test
	public void test_getOne_returns_teacher_returned_from_repo() throws EmptyResultDataAccessException {
		//arrange
		Teacher teacher = new Teacher(); 
		when(teacherRepo.findOne(1L)).thenReturn(teacher); 
		
		//act
		TeacherFullDto actual = controller.getOne(1L); 
		
		//assert
		assertThat(actual).isSameAs(teacher);
		verify(teacherRepo).findOne(1L); 
	}
	
//	@Test
//	public void test_getOne_throws_StuffNotFoundException_when_no_teacher_returned_from_repo() {
//		try {
//			controller.getOne(1L); 
//			fail("The controller did not throw the EmptyResultDataAccessException."); 
//		} catch(EmptyResultDataAccessException snfe) {
//			
//		}
//	}
	

	
	@Test
	public void test_teacher_is_created_when_create_is_called() {
		//arrange
		Teacher rick = new Teacher("Rick", "Morty", "rm@gmail.com", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenReturn(null);
		when(encoder.encode("password")).thenReturn("Liberty Mutual Rocks!");
		
		//act
		HttpServletResponse response = null;
		TeacherDto actual = controller.create(rick, response); 
		
		//assert
		assertThat(rick.getEmail()).isSameAs(actual.getEmail()); 
		assertThat(rick.getPassword()).isEqualTo("Liberty Mutual Rocks!");
		verify(teacherRepo).save(rick); 
	}

	@Test
	public void test_teacher_is_not_created_when_already_exists() {
		//arrange
		Teacher rick = new Teacher("Rick", "Morty", "rm@gmail.com", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenThrow(new DataIntegrityViolationException("Teacher in request body was not valid"));
		
		//act
		HttpServletResponse response = mock(HttpServletResponse.class);
		TeacherDto actual = controller.create(rick, response); 
		
		//assert
		assertThat(actual).isNull();
		
		
	}
	
	@Test
	public void test_teacher_is_not_created_when_already_exists2() {
		//arrange
		Teacher rick = new Teacher("Rick", "Morty", "rm@gmail.com", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenReturn(rick);
		
		//act
		HttpServletResponse response = mock(HttpServletResponse.class);
		TeacherDto actual = controller.create(rick, response); 
		
		//assert
		assertThat(actual).isNull();
		
		
	}
	
	
	
	@Test
	public void test_teacher_is_updated_when_update_is_called() {
		//arrange
		Teacher teacher = new Teacher("firstName", "lastName", "email", "password", 8, "TEACHER"); 
		when(teacherRepo.findOne(1L).getPassword()).thenReturn("password");
		when(encoder.encode("password")).thenReturn("apiTest");
		when(teacherRepo.save(teacher)).thenReturn(teacher);
		
		//act
		TeacherDto actual = controller.update(teacher, id); 
		
		//assert
		assertThat(teacher.getEmail()).isSameAs(actual.getEmail()); 
		assertThat(teacher.getPassword()).isEqualTo("apiTest");
		verify(teacherRepo).save(teacher); 
	}

	@Test
	public void test_teacher_is_not_updated_when_already_exists() {
		//arrange
		Teacher teacher = new Teacher("firstName", "lastName", "email", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenThrow(new DataIntegrityViolationException("Teacher in request body was not valid"));
		
		//act
		TeacherDto actual = controller.update(teacher, id);
		
		//assert
		assertThat(actual).isNull();
			
	}
	
	@Test
	public void test_teacher_is_not_updated_when_already_exists2() {
		//arrange
		Teacher teacher = new Teacher("firstName", "lastName", "email", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenReturn(teacher);
		
		//act
		HttpServletResponse response = mock(HttpServletResponse.class);
		TeacherDto actual = controller.update(teacher, id);
		
		//assert
		assertThat(actual).isNull();
		
	}
	
	@Test
	public void test_null_is_returned_when_findOne_teacher_throws_EmptyResultDataAccessException() {
		//arrange
		when(teacherRepo.findOne(1L)).thenThrow(new EmptyResultDataAccessException(0)); 
		
		//act
		TeacherDto actual = controller.delete(1L); 
		
		//assert
		assertThat(actual).isNull(); 
		verify(teacherRepo).findOne(1L); 
		
	}
	
	@Test
	public void test_teacher_is_deleted_when_delete_is_called() {
		//arrange
		Teacher teacher = new Teacher("Rick", "Morty", "rm@gmail.com", "password", 8, "TEACHER"); 
		List<Student> students = studentRepository.findByTeacher(teacher);
		when(students).thenReturn(students);
		when(encoder.encode("password")).thenReturn("Api Test");
		
		//act
		TeacherDto actual = controller.delete(1L);
		
		//assert
		assertThat(teacher.getEmail()).isSameAs(actual.getEmail()); 
		assertThat(teacher.getPassword()).isEqualTo("Api Test");
		verify(teacherRepo).save(teacher); 
	}

	@Test
	public void test_teacher_is_deleted_when_already_exists() {
		//arrange
		Teacher rick = new Teacher("Rick", "Morty", "rm@gmail.com", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenThrow(new DataIntegrityViolationException("Teacher in request body was not valid"));
		
		//act
		TeacherDto actual = controller.delete(1L);
		
		//assert
		assertThat(actual).isNull();
		
		
	}
	
	@Test
	public void test_teacher_is_deleted_when_already_exists2() {
		//arrange
		Teacher rick = new Teacher("Rick", "Morty", "rm@gmail.com", "password", 8, "TEACHER"); 
		when(userRepo.findByEmail("rm@gmail.com")).thenReturn(rick);
		
		//act
		HttpServletResponse response = mock(HttpServletResponse.class);
		TeacherDto actual = controller.delete(id);
		
		//assert
		assertThat(actual).isNull();
		
		
	}
	
	
}