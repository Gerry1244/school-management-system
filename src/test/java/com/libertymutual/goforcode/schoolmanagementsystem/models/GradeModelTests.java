package com.libertymutual.goforcode.schoolmanagementsystem.models;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class GradeModelTests {

	 @Test
	 public void gettersAndSettersShouldFunctionCorrectly() {
	 BeanTester tester = new BeanTester();
	 tester.testBean(Grade.class);
	 }
	 
	 @Test
	 public void test_required_argument_constructors() {
		//Arrange/Act Same step for constructors...
		 Grade grade = new Grade("B+");
		 
		 //Assert
		  
		 assertThat(grade.getLetterGradeValue()).isEqualTo("B+");
		 
	 }
	 
}
