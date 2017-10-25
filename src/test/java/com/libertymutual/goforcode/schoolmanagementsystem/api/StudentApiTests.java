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

import com.libertymutual.goforcode.schoolmanagementsystem.dto.AssignmentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.StudentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Announcement;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.services.EmailApiService;

public class StudentApiTests {
	private StudentRepository studentRepo;
	private StudentApiController controller;
	private TeacherRepository teacherRepo;
	private EmailApiService emailService;;

	@Before
	public void setUp() {
		studentRepo = mock(StudentRepository.class);
		teacherRepo = mock(TeacherRepository.class);
		emailService = mock(EmailApiService.class);
		controller = new StudentApiController(studentRepo, teacherRepo, null, null, null, null, emailService);
	}

	@Test
	public void setUp_test_getAll_students() {
		// Arrange
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student());
		students.add(new Student());
		when(studentRepo.findAll()).thenReturn(students);
		// Act
		List<StudentDto> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(controller.getAll().size()).isEqualTo(2);
 
	}


	@Test
	public void setUp_test_create_students() {
		// Arrange
		Student student = new Student();
		when(studentRepo.save(student)).thenReturn(student);

		// act
		long id = 1L;
		HttpServletResponse response = null;
		StudentDto actual = controller.create(student, id, response);

		// assert
		//assertThat(student).get(actual);
		assertThat(student.getId()).isEqualTo(actual.getUserId());
		assertThat(student.getFirstName()).isEqualTo(actual.getFirstName());
		assertThat(student.getLastName()).isEqualTo(actual.getLastName());
		assertThat(student.getRoleName()).isEqualTo(actual.getRoleName());
		assertThat(student.getGradeLevel()).isEqualTo(actual.getGradeLevel());
		verify(studentRepo).save(student);

	}  

	@Test
	public void test_delete_students() {
		// arrange
		Student student = new Student();
		when(studentRepo.findOne(1L)).thenReturn(student);

		// act
		StudentDto actual = controller.delete(1L);

		// assert
		// assertThat(student).isSameAs(actual);
		verify(studentRepo).delete(1L);

	}
	


	@Test
	public void test_null_when_save_student_sends_EmptyResultDataAccessException() {
		// arrange
		when(studentRepo.findOne(1L)).thenThrow(new EmptyResultDataAccessException(0));

		// Act
		StudentDto actual = controller.delete(1L);

		// Assert
		assertThat(actual).isNull();
		verify(studentRepo).findOne(1L);

	}

	@Test
	public void test_student_when_id_is_updated() {
		// Arrange
		Student student = new Student();
		student.setId(1L);
		Teacher teacher = new Teacher();
		teacher.setId(3L);
		when(studentRepo.getOne(1L)).thenReturn(student);
		// Act
		StudentDto actual = controller.update(student, 1L, 3L);

		// Assert
		verify(studentRepo).save(student);

	}

	@Test
	public void test_null_when_update_student_sends_EmptyResultDataAccessException() {
		// Arrange
		Student student = new Student();
		when(studentRepo.save(student)).thenThrow(new EmptyResultDataAccessException(0));

		// act

		StudentDto actual = controller.update(student, 1L, 3L);

		// assert
		assertThat(actual).isNull();

	}


	@Test
	public void test_null_when_update_student_sends_DataIntegrityViolationException() {
		// Arrange
		Student student = new Student();
		when(studentRepo.save(student)).thenThrow(new DataIntegrityViolationException(null));

		// act

		StudentDto actual = controller.update(student, 1L, 3L);

		// assert
		assertThat(actual).isNull();

	}

	
}
