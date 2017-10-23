package com.libertymutual.goforcode.schoolmanagementsystem.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import java.util.Date;

public class AnnouncementModelTests {

		 @Test
		 public void gettersAndSettersShouldFunctionCorrectly() {
		 BeanTester tester = new BeanTester();
		 tester.testBean(Announcement.class);
		 }
		 
		 @Test
		 public void test_required_argument_constructors() {
			//Arrange/Act Same step for constructors...
			 Announcement announcement = new Announcement("This is an actual title", "This is an actual description");
			 
			 //Assert
			 
			 assertThat(announcement.getTitle()).isEqualTo("This is an actual title");
			 assertThat(announcement.getDescription()).isEqualTo("This is an actual description");
			 
		 }
		 
		 @Test
		 public void test_full_argument_constructors() {
			//Arrange/Act Same step for constructors...
			 Announcement announcement = new Announcement("This is an actual title", "This is an actual description", new Date(Date.parse("07/16/1967")));
			 
			 //Assert
			 
			 assertThat(announcement.getTitle()).isEqualTo("This is an actual title");
			 assertThat(announcement.getDescription()).isEqualTo("This is an actual description");
			 assertThat(announcement.getDate()).isEqualTo(new Date(Date.parse("07/16/1967")));
		 }

} 
