package com.libertymutual.goforcode.schoolmanagementsystem.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Admin;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AdminRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;


@Configuration
@Profile("development") //will only load this configuration (bean) if active spring profile is named "development"
public class SeedData {
	
	public SeedData(UserRepository userRepository, PasswordEncoder encoder, StudentRepository studentRepo, 
					TeacherRepository teacherRepo, AdminRepository adminRepo, AssignmentRepository assignmentRepo) {
		Teacher garyTheTeacher = new Teacher("Gary", "Gossage", "gg@gmail.com", encoder.encode("password"), 7, "TEACHER");
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
		
		Assignment garysAssignment = new Assignment("Gary Gossage's assignment for 7th grade class");
		assignmentRepo.save(garysAssignment);
	
		garysAssignment.setStudents(garysStudents);

		
		studentRepo.save(new Student("MyButt", "InYourFace", "mb@gmail.com", encoder.encode("password"), 5, "STUDENT"));
		
		adminRepo.save(new Admin("Mary", "Richards", "mr@gmail.com", encoder.encode("password"), "ADMIN"));	
		assignmentRepo.save(new Assignment("Essay on some dumb topic"));
	}

}
