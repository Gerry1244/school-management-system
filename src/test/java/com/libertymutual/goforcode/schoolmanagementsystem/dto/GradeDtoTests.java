package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class GradeDtoTests {

	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(AssignmentDto.class);
	}
	
}
