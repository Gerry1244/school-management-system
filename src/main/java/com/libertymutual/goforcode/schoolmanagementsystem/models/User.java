package com.libertymutual.goforcode.schoolmanagementsystem.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "Person")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 75, nullable = false)
	protected String firstName;

	@Column(length = 75, nullable = false)
	protected String lastName;

	@Column(length = 75, nullable = false)
	protected String email;

	@Column(length = 75, nullable = false)
	protected String password;
	
	@Column(length= 75, nullable=false)
	String roleName;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, String roleName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roleName = roleName;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<String> roleNames = roles.stream().map(userRole -> "ROLE_" + userRole.getName()) // userRole is a variable
//																								// name, represents
//																								// individual user role
//																								// in List<UserRole>
//				.collect(Collectors.toList());
//
//		String authorityString = String.join(",", roleNames); // turns into comma seperated list
//		return AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
