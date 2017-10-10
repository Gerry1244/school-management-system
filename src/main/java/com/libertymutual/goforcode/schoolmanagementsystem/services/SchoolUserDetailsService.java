package com.libertymutual.goforcode.schoolmanagementsystem.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;

@Service
public class SchoolUserDetailsService implements UserDetailsService {
	
private UserRepository usersRepository;
	
	public SchoolUserDetailsService(UserRepository usersRepository) {
		this.usersRepository = usersRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return user;
	}

}
