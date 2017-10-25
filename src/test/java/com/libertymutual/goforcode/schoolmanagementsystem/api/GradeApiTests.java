package com.libertymutual.goforcode.schoolmanagementsystem.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.GradeDto;
import com.libertymutual.goforcode.schoolmanagementsystem.dto.TeacherDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.models.UpdateLetterGradeModel;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;



public class GradeApiTests {
	private GradeRepository gradeRepo;
	private GradeApiController controller;
	private int List;
	
	@Before
	public void setUp() {
		gradeRepo = mock(GradeRepository.class);  
		controller = new GradeApiController(gradeRepo, null, null);   
	}
	
	@Test
	public void test_get_a_single_grade_by_id() {
		//Arrange
		Grade grade = new Grade("letterGradeValue");
		grade.setId(1L);
		when(gradeRepo.findOne(1L)).thenReturn(grade);
		
		//Act
		GradeDto actual = controller.getOne(1L);
		
		//Assert
		assertThat(grade.getLetterGradeValue()).isEqualTo(actual.getletterGradeValue()); 
		verify(gradeRepo).findOne(1L);
		
	}
	
	@Test
	public void test_when_grade_returns_null() {
		//Arrange
		Grade grade = new Grade("letterGradeValue");
		grade.setId(1L);
		when(gradeRepo.findOne(1L)).thenReturn(grade);
		
		//Act
		GradeDto actual = controller.getOne(1L);
		
		//Assert
		assertThat(actual).isNull();
	}
	
	@Test
	public void test_full_list_of_grades_for_both_assignments_and_students() {
		//Arrange
		ArrayList<Grade> grades = new ArrayList<Grade>(); 
		Grade grade = new Grade();
		Grade grade2 = new Grade();
		grades.add(grade);
		grades.add(grade2);
		when(gradeRepo.findAll()).thenReturn(grades);
		
		//Act
		List<GradeDto> actual = controller.getAll(); 
		
		//Assert
		assertThat(actual.size()).isEqualTo(2); 
		verify(gradeRepo).findAll(); 	
			
	}
	
	
//	@Test
//	public void test_grade_when_id_is_updated() {
//		//Arrange
//		Grade grade = new Grade();
//		grade.setId(1L);
//		when(gradeRepo.getOne(1L)).thenReturn(grade);
//		//Act
//		long id = 1L;
//		Grade actual = controller.
//		
//		//Assert
//		assertThat(grade.getLetterGradeValue()).isEqualTo(actual); 
//		verify(gradeRepo).save(grade); 
//	}

}
