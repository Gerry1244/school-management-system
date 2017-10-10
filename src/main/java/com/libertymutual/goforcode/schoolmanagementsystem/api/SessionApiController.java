package com.libertymutual.goforcode.schoolmanagementsystem.api;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description="This controller deals with loggin in, and session information.")
public class SessionApiController {

	private UserRepository userRepo;
	private PasswordEncoder encoder;

	public SessionApiController(UserRepository userRepo, PasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	@ApiOperation(value = "Login as a user")
	@PostMapping("login")
	public User login(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		User userInDatabase = new User();
		if (userRepo.findByEmail(email) != null) {
			userInDatabase = userRepo.findByEmail(email);

			String encryptedPassword = userInDatabase.getPassword();
			// encryptedPassword = encoder

			if (encryptedPassword == password) {
				// user is a match
				return user;
			}

			// userRepo.findByPassword(password).equals(encoder.encode(password));
		}
		return user;
	}

}
