package com.libertymutual.goforcode.schoolmanagementsystem.configuration;

import org.springframework.context.annotation.Bean;
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
				.csrf().disable()
				.authorizeRequests()
					.antMatchers("/**").permitAll()
//					.antMatchers("/invoices/**").hasAnyRole("ADMIN", "ACCOUNTANT")
//					.antMatchers("/billing-records/**").hasAnyRole("ADMIN", "CLERK")
//					.antMatchers("/admin/**").hasRole("ADMIN") 	
					.anyRequest().authenticated(); //any request that comes through security pipeline has to be authenticated
//				.and()	
//				.formLogin();							
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
