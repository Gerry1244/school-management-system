package com.libertymutual.goforcode.schoolmanagementsystem.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.UpdateLetterGradeModel;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;



public class GradeApiTests {
	private GradeRepository gradeRepo;
	private GradeApiController controller;
	
	@Before
	public void setUp() {
		gradeRepo = mock(GradeRepository.class);
		controller = new GradeApiController(gradeRepo, null, null); 
	}


	@Test
	public void test_grade_when_id_is_updated() {
		//Arrange
		Grade grade = new Grade();
		grade.setId(1L);
		when(gradeRepo.getOne(1L)).thenReturn(grade);
		//Act
		Grade actual = controller.update(grade, 1L);
		
		//Assert
		assertThat(grade).isSameAs(actual); 
		verify(gradeRepo).save(grade); 
	}

}
