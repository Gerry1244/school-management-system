package com.libertymutual.goforcode.schoolmanagementsystem.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Admin;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Announcement;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AdminRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AnnouncementRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;


@Configuration
@Profile("development") //will only load this configuration (bean) if active spring profile is named "development"
public class SeedData {
	
	public SeedData(UserRepository userRepository, PasswordEncoder encoder, StudentRepository studentRepo, 
					TeacherRepository teacherRepo, AdminRepository adminRepo, AssignmentRepository assignmentRepo, 
					AnnouncementRepository announcementRepo,  GradeRepository gradeRepo) {
		Teacher garyTheTeacher = new Teacher("Gary", "Gossage", "gg@gmail.com", encoder.encode("password"), 5, "TEACHER");
		teacherRepo.save(garyTheTeacher);
		
		Student student1 = new Student("Richard", "Sandoval", "rs@gmail.com", encoder.encode("password"), 5, "STUDENT");
		Student student2 = new Student("Lenny", "Kravitz", "lc@gmail.com", encoder.encode("password"), 5, "STUDENT");
		Student student3 = new Student("MyButt", "InYourFace", "mb@gmail.com", encoder.encode("password"), 5, "STUDENT");	
		
		student1.setTeacher(garyTheTeacher);
		student2.setTeacher(garyTheTeacher);
		student3.setTeacher(garyTheTeacher);
		
		studentRepo.save(student1);
		studentRepo.save(student2);
		studentRepo.save(student3);
		List<Student> garysStudents = studentRepo.findByTeacher(garyTheTeacher);
		
		
		Assignment garysAssignment = new Assignment("Your first essay", "Gary Gossage's Essay assignment for 7th grade class", "You kids are done for", garyTheTeacher);
		Assignment garysAssignment2 = new Assignment("Derivatives", "Gary Gossage's Math assignment for 7th grade class", "You math idiots are done for :P", garyTheTeacher);
		Assignment garysAssignment3 = new Assignment("Gary Gossage's second assignment for 7th grade class");
		garysAssignment.setStudents(garysStudents);
		garysAssignment2.setStudents(garysStudents);
		garysAssignment3.setStudents(garysStudents);
		
		assignmentRepo.save(garysAssignment);
		assignmentRepo.save(garysAssignment2);
		assignmentRepo.save(garysAssignment3);
		
		//Assign default grades to garysAssignment
		for (Student student : garysStudents) {
			Grade grade = new Grade();
			grade.setAssignment(garysAssignment);
			grade.setStudent(student);
			grade.setLetterGradeValue("Not graded.");	
			gradeRepo.save(grade);
		}
		//Assign default grades to garysAssignment2
		for (Student student : garysStudents) {
			Grade grade = new Grade();
			grade.setAssignment(garysAssignment2);
			grade.setStudent(student);
			grade.setLetterGradeValue("Not graded.");	
			gradeRepo.save(grade);
		}
		
		//Assign default grades to garysAssignment2
				for (Student student : garysStudents) {
					Grade grade = new Grade();
					grade.setAssignment(garysAssignment3);
					grade.setStudent(student);
					grade.setLetterGradeValue("Not graded.");	
					gradeRepo.save(grade);
				}
		
		
		//------------------ 8============================D  ~ ~ ~ ~ ~ --------------------//
		//Orphan data
		
		studentRepo.save(new Student("Little", "Jimmy", "lj@gmail.com", encoder.encode("password"), 6, "STUDENT"));	
		teacherRepo.save(new Teacher("Matt", "Meyers", "mm@gmail.com", encoder.encode("password"), 5, "TEACHER"));
		adminRepo.save(new Admin("Mary", "Richards", "mr@gmail.com", encoder.encode("password"), "ADMIN"));	
		assignmentRepo.save(new Assignment("Essay on some dumb topic"));
		
		announcementRepo.save(new Announcement("drawing", "draw some shit"));
		announcementRepo.save(new Announcement("The Iron Yard is closing.", "We weren't very profitable so we're closing"));
		
	}

}
