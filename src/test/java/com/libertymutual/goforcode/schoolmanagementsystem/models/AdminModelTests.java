package com.libertymutual.goforcode.schoolmanagementsystem.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class AdminModelTests {

	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(Admin.class);
	}

	@Test
	public void test_required_argument_constructors() {
		// Arrange/Act Same step for constructors...
		Admin admin = new Admin("Harvey", "Williamson", "hw@gmail.com", "password", "ADMIN");
		// Assert
		assertThat(admin.getFirstName()).isEqualTo("Harvey");
		assertThat(admin.getLastName()).isEqualTo("Williamson");
		assertThat(admin.getEmail()).isEqualTo("hw@gmail.com");
		assertThat(admin.getPassword()).isEqualTo("password");
		assertThat(admin.getRoleName()).isEqualTo("ADMIN");

	}
	
	@Test public void test_get_serial_version_uid() {
		//Arrange
		Admin admin = new Admin();
		
		//Act
		Long actual = admin.getSerialversionuid();
		
		//Assert
		assertThat(actual).isEqualTo(1L);
	}

}

