package com.libertymutual.goforcode.schoolmanagementsystem.api;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.schoolmanagementsystem.models.User;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.UserRepository;

public class UserApiTests {
	private UserRepository userRepo;
	private UserApiController controller;

	@Before
	public void setUp() {
		userRepo = mock(UserRepository.class);
		controller = new UserApiController(userRepo, null, null);
	}

	@Test
	public void setUp_test_getAll_users() {
		// Arrange
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		when(userRepo.findAll()).thenReturn(users);
		// Act
		List<User> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		// assertThat(controller.getAll().size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(users.get(0));
		verify(userRepo).findAll();

	}

	@Test
	public void setUp_test_create_user() {
		// Arrange
		User user = new User();
		when(userRepo.save(user)).thenReturn(user);

		// act
		User actual = controller.create(user);

		// assert
		assertThat(user).isSameAs(actual);
		verify(userRepo).save(user);
	}

	
		
		@Test
		public void test_user_when_id_is_updated() {
			//Arrange
			User user = new User();
			user.setId(1L);
			when(userRepo.getOne(1L)).thenReturn(user);
			//Act
			User actual = controller.update(user, 1L);
			
			//Assert
			assertThat(user).isSameAs(actual); 
			verify(userRepo).save(user); 
		}

}

