package com.libertymutual.goforcode.schoolmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Admin;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AdminRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

@SpringBootApplication
public class SchoolManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}
	
	public SchoolManagementSystemApplication(StudentRepository studentRepo, TeacherRepository teacherRepo, AdminRepository adminRepo) {
		studentRepo.save(new Student("Richard", "Sandoval", "rs@gmail.com", "password", 5));
		teacherRepo.save(new Teacher("Gary", "Gossage", "gg@gmail.com", "salad", 7));
		adminRepo.save(new Admin("Mary", "Richards", "mr@gmail.com", "password"));
	}
}
