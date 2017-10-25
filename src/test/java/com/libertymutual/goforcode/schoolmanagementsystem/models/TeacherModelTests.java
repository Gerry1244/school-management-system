package com.libertymutual.goforcode.schoolmanagementsystem.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class TeacherModelTests {

	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(Admin.class);
	}
	
	@Test
	public void test_required_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Student student = new Student("Harvey", "Williamson", "hw@gmail.com", "password", 5, "STUDENT");
		// Assert
		assertThat(student.getFirstName()).isEqualTo("Harvey");
		assertThat(student.getLastName()).isEqualTo("Williamson");
		assertThat(student.getEmail()).isEqualTo("hw@gmail.com");
		assertThat(student.getPassword()).isEqualTo("password");
		assertThat(student.getGradeLevel()).isEqualTo(5);
		assertThat(student.getRoleName()).isEqualTo("STUDENT");

	}

}