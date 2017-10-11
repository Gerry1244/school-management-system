package com.libertymutual.goforcode.schoolmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Admin;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AdminRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

@SpringBootApplication
public class SchoolManagementSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}

	public SchoolManagementSystemApplication(PasswordEncoder encoder, StudentRepository studentRepo, TeacherRepository teacherRepo, AdminRepository adminRepo, AssignmentRepository assignmentRepo) {
		studentRepo.save(new Student("Richard", "Sandoval", "rs@gmail.com", encoder.encode("password"), 5, "STUDENT"));
		studentRepo.save(new Student("Lenny", "Kravitz", "lc@gmail.com", encoder.encode("password"), 5, "STUDENT"));	
		studentRepo.save(new Student("MyButt", "InYourFace", "mb@gmail.com", encoder.encode("password"), 5, "STUDENT"));
		teacherRepo.save(new Teacher("Gary", "Gossage", "gg@gmail.com", encoder.encode("password"), 7, "TEACHER"));
		adminRepo.save(new Admin("Mary", "Richards", "mr@gmail.com", encoder.encode("password"), "ADMIN"));	
		assignmentRepo.save(new Assignment("Essay on some dumb topic"));
		
	}
}
