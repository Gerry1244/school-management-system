package com.libertymutual.goforcode.schoolmanagementsystem.api;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.services.SchoolUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
@Api(description="This controller deals with users.")
public class UserApiController {
	
	private UserRepository userRepo;
	private PasswordEncoder encoder;
	private SchoolUserDetailsService schoolUserDetails;

	public UserApiController(UserRepository userRepo, PasswordEncoder encoder, SchoolUserDetailsService schoolUserDetails) {
		this.userRepo = userRepo;
	}
	
	@ApiOperation(value = "Update user's information.")
	@PutMapping("{id}")
	public User update(@RequestBody User user, @PathVariable long id) {
		user.setId(id);
		return userRepo.save(user);
	}
	
	@ApiOperation(value = "Create a new user")
	@PostMapping("/signup")
	public User create(@RequestBody User user) throws DataIntegrityViolationException {
//		String password = user.getPassword();
//		String encryptedPassword = encoder.encode(password);
//		user.setPassword(encryptedPassword);
		final String roleName = user.getRoleName();
		User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), encoder.encode(user.getPassword()), user.getRoleName());

//		try {
//			userRepo.save(newUser);
//		}
		return newUser;
		//watcher = watchers.save(watcher);
		
	}

}
