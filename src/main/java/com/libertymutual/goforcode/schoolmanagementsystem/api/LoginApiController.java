package com.libertymutual.goforcode.schoolmanagementsystem.api;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AdminRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/login")
public class LoginApiController {
	
	private StudentRepository studentRepo;
	private AdminRepository adminRepo;
	private TeacherRepository teacherRepo;
	private PasswordEncoder encoder;
	
	public LoginApiController(StudentRepository studentRepo, AdminRepository adminRepo, TeacherRepository teacherRepo, PasswordEncoder encoder ) {
		this.studentRepo = studentRepo;
		this.adminRepo = adminRepo;
		this.teacherRepo = teacherRepo;
		this.encoder = encoder;
	}
	
	@ApiOperation(value="Login a user")	
	@PostMapping("")
	public User login(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		
		return user;
	}
	
	

}
