package com.libertymutual.goforcode.schoolmanagementsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libertymutual.goforcode.schoolmanagementsystem.services.SchoolUserDetailsService;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	private SchoolUserDetailsService schoolUserDetailsService;
	
		public SecurityConfiguration(SchoolUserDetailsService schoolUserDetailsService) {
			this.schoolUserDetailsService = schoolUserDetailsService;
		
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
				.antMatchers("/**").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/teachers", "/api/students/*").hasRole("ADMIN")
//				.antMatchers(HttpMethod.POST, "/api/assignments").hasAnyRole("TEACHER", "ADMIN")
//				.antMatchers(HttpMethod.DELETE, "/api/teachers/*", "/api/students/*").hasRole("ADMIN")
//				.antMatchers(HttpMethod.PUT, "/api/teachers/*", "/api/students/*").hasRole("ADMIN")
//				.antMatchers(HttpMethod.PUT, "/api/session").permitAll()
//				.antMatchers(HttpMethod.DELETE, "/api/session").permitAll()
//				.antMatchers(HttpMethod.GET, "/api/teachers", "/api/students", "/api/assignments", "/api/assignments/*").permitAll()
//				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated() //any request that comes through security pipeline has to be authenticated					
//				.formLogin();
			.and()
				.csrf().disable();
		}
		
	@Bean
	public PasswordEncoder passwordEndoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override //beans are discoverable by spring
	//allows spring to get usernames, passwords, and roles; fluent API
	//this is just an in memory service that overrides default spring behavior
	public UserDetailsService userDetailsService() {	
		return schoolUserDetailsService;
	}

}
