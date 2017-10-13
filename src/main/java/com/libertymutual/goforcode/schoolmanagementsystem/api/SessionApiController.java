package com.libertymutual.goforcode.schoolmanagementsystem.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.models.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/session")
@Api(description = "This controller deals with loggin in, and session information.")
public class SessionApiController {

	// private UserRepository userRepo;
	// private PasswordEncoder encoder;

	private UserDetailsService userDetails;
	private AuthenticationManager authenticator;

	public SessionApiController(UserDetailsService userDetails, AuthenticationManager authenticator) {
		this.userDetails = userDetails;
		this.authenticator = authenticator;
	}
	

	@ApiOperation(value = "Log a user in.")
	@PutMapping("")
	public UserDto login(@RequestBody Credentials credentials, Authentication auth) {
		System.out.println("login method ran");

		User user = (User) userDetails.loadUserByUsername(credentials.getUsername());

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, credentials.password, user.getAuthorities());

		authenticator.authenticate(token);
		if (token.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(token);
		};
		return new UserDto(user);
	}

	
	@ApiOperation(value = "Logout a user.")
	@DeleteMapping("")
	public Boolean logout(Authentication auth, HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, auth);
		return true;
	}

	static class Credentials {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

}
