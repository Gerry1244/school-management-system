
package com.libertymutual.goforcode.schoolmanagementsystem.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserModelTests {

	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(Student.class);
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
		assertThat(student.getRoleName()).isEqualTo("STUDENT");

	}

}
