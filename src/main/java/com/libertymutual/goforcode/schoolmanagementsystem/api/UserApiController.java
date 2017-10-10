package com.libertymutual.goforcode.schoolmanagementsystem.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
@Api(description="This controller deals with users.")
public class UserApiController {
	
	private UserRepository userRepo;

	public UserApiController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@ApiOperation(value = "Update user's information.")
	@PutMapping("{id}")
	public User update(@RequestBody User user, @PathVariable long id) {
		user.setId(id);
		return userRepo.save(user);
	}

}
