package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import static org.junit.Assert.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class StudentFullDtoTests {

	@Test
	public void gettersAndSettersShouldFunctionCorrectly() {
		BeanTester tester = new BeanTester();
		tester.testBean(AssignmentDto.class);
	}

}
