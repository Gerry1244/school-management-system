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
				.antMatchers("/").permitAll()	
				.antMatchers("/api/announcements").permitAll()	
				.antMatchers("/**").permitAll()	
				
				.antMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
				
				.antMatchers(HttpMethod.PUT, "/api/session").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/session").permitAll()
				
				.antMatchers(HttpMethod.POST, "/api/assignments", "/api/announcements").hasRole("TEACHER")
				.antMatchers(HttpMethod.DELETE, "/api/assignments/{id}","/api/announcements/{id}").hasRole("TEACHER")
				.antMatchers(HttpMethod.PUT, "/api/teachers/{id}", "/api/students/{id}","/api/grades/{id}", "/api/assignments/{id}", "/api/announcments/{id}").hasRole("TEACHER")
				.antMatchers(HttpMethod.GET, "/api/teachers/{id}", "/api/teachers/{id}/assignments","/api/teachers", "/api/students/{id}", "/api/students/{id}/assignments", "/api/students", 
											 "/api/grade-level/{gradeLevel}/teachers", "/api/grades/{id}", "/api/grades/assigments/{id}", "/api/grades/students/{id}", "/api/assignments/{id}", 
											 "/api/assignments/{id}/students", "/api/assignments", "/api/accouncements").hasRole("TEACHER")
				
				.antMatchers(HttpMethod.PUT, "/api/teachers/{id}", "/api/students/{id}").hasRole("STUDENT")
				.antMatchers(HttpMethod.GET, "/api/students/{id}", "/api/students/{id}/assignments", "/api/grades/students/{id}", "/api/assignments/{id}", "/api/accouncements").hasRole("STUDENT")
				
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
