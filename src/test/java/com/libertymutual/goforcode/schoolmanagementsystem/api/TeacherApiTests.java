package com.libertymutual.goforcode.schoolmanagementsystem.api;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

public class TeacherApiTests {
	private TeacherRepository teacherRepo;
	private TeacherApiController controller;

	@Before
	public void setUp() {
		teacherRepo = mock(TeacherRepository.class);
		controller = new TeacherApiController(teacherRepo);
	}

	@Test
	public void setUp_test_getAll_teachers() {
		// Arrange
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		teachers.add(new Teacher());
		teachers.add(new Teacher());
		when(teacherRepo.findAll()).thenReturn(teachers);
		// Act
		List<Teacher> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		// assertThat(controller.getAll().size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(teachers.get(0));
		verify(teacherRepo).findAll();

	}

	@Test
	public void setUp_test_create_teacher() {
		// Arrange
		Teacher teacher = new Teacher();
		when(teacherRepo.save(teacher)).thenReturn(teacher);

		// act
		Teacher actual = controller.create(teacher);

		// assert
		assertThat(teacher).isSameAs(actual);
		verify(teacherRepo).save(teacher);
	}

	@Test
	public void setUp_test_delete_teachers() {
		// arrange
		Teacher teacher = new Teacher();
		when(teacherRepo.findOne(1L)).thenReturn(teacher);

		// act
		Teacher actual = controller.delete(1L);

		// assert
		assertThat(teacher).isSameAs(actual);
		verify(teacherRepo).delete(1L);
		
	}
		
		@Test
		public void test_teacher_when_id_is_updated() {
			//Arrange
			Teacher teacher = new Teacher();
			teacher.setId(1L);
			when(teacherRepo.getOne(1L)).thenReturn(teacher);
			//Act
			Teacher actual = controller.update(teacher, 1L);
			
			//Assert
			assertThat(teacher).isSameAs(actual); 
			verify(teacherRepo).save(teacher); 
		}

}