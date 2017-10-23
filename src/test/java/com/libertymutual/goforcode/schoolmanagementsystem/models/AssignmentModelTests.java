package com.libertymutual.goforcode.schoolmanagementsystem.models;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class AssignmentModelTests {

	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(Assignment.class);
	}

	@Test
	public void test_full_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Assignment assignment = new Assignment("Week3 Assignment", "History of Civil War",
				new Date(Date.parse("07/16/1967")), "Well done on project");

		// Assert
		assertThat(assignment.getName()).isEqualTo("Week3 Assignment");
		assertThat(assignment.getDescription()).isEqualTo("History of Civil War");
		assertThat(assignment.getDueDate()).isEqualTo(new Date(Date.parse("07/16/1967")));
		assertThat(assignment.getComment()).isEqualTo("Well done on project");

	}

	@Test
	public void test2_full_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Teacher teacher = new Teacher();
		Assignment assignment = new Assignment("Week3 Assignment", "History of Civil War",
				new Date(Date.parse("07/16/1967")), "Well done on project",
				teacher);

		// Assert
		assertThat(assignment.getName()).isEqualTo("Week3 Assignment");
		assertThat(assignment.getDescription()).isEqualTo("History of Civil War");
		assertThat(assignment.getDueDate()).isEqualTo(new Date(Date.parse("07/16/1967")));
		assertThat(assignment.getComment()).isEqualTo("Well done on project");
		assertThat(assignment.getTeacher()).isEqualTo(teacher);

	}
	
	@Test
	public void test3_full_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Teacher teacher = new Teacher();
		Assignment assignment = new Assignment("Week3 Assignment", "History of Civil War",
				 "Well done on project",
				teacher);

		// Assert
		assertThat(assignment.getName()).isEqualTo("Week3 Assignment");
		assertThat(assignment.getDescription()).isEqualTo("History of Civil War");
		assertThat(assignment.getComment()).isEqualTo("Well done on project");
		assertThat(assignment.getTeacher()).isEqualTo(teacher);

	}
	
	@Test
	public void test_required_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Assignment assignment = new Assignment("Week3 Assignment");

		// Assert
		assertThat(assignment.getName()).isEqualTo("Week3 Assignment");
	}


	


}

