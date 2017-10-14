package com.libertymutual.goforcode.schoolmanagementsystem.dto;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;

public class UserDto {

private User user;
	
	public UserDto(User user) {
		this.user = user;
	}
	
	public Long getUserId() {
		return user.getId();
	}
	
	public String getFirstName() {
		return user.getFirstName();
	}
	
	public String getLastName() {
		return user.getLastName();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public String getRoleName() {
		return user.getRoleName();
	}

}
