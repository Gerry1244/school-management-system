package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;

public class AssignmentDtoTests {

	
//	private AssignmentDto dto;
//	
//	@Before
//	public void setup() {
//		dto = mock(AssignmentDto.class);
//	}
	
	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(AssignmentDto.class);
	}

	@Test
	public void test_required_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Assignment assignment = new Assignment("Week3 Assignment");

		// Assert
		assertThat(assignment.getName()).isEqualTo("Week3 Assignment");

	}

	
	
	
	
//	@Test
//	public void test_that_getAssignmentId_returns_the_correct_id() {
//		Assignment assignment = new Assignment();
//		assignment.setId(1l);
//		
//		long actual = dto.getAssignmentId();
//		
//		assertThat(actual).isEqualTo(1l);
//		
//	}
	
}
