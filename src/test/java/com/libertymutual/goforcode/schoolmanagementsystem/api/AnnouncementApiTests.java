package com.libertymutual.goforcode.schoolmanagementsystem.api;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Announcement;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AnnouncementRepository;

public class AnnouncementApiTests {
	private AnnouncementRepository announcementRepo;
	private AnnouncementApiController controller;

	@Before
	public void setUp() {
		announcementRepo = mock(AnnouncementRepository.class);
		controller = new AnnouncementApiController(announcementRepo);
	}

	@Test
	public void setUp_test_getAll_announcements() {
		// Arrange
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		announcements.add(new Announcement());
		announcements.add(new Announcement());
		when(announcementRepo.findAll()).thenReturn(announcements); 
		// Act
		List<Announcement> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		// assertThat(controller.getAll().size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(announcements.get(0));
		verify(announcementRepo).findAll();

		when(announcementRepo.findOne(1l)).thenThrow(new EmptyResultDataAccessException(0));
	}
	
	@Test
	public void test_getOne_returns_announcement_returned_from_repo() throws EmptyResultDataAccessException {
		//arrange
		Announcement announcement = new Announcement(); 
		when(announcementRepo.findOne(1L)).thenReturn(announcement); 
		
		//act
		Announcement actual = controller.getOne(1L); 
		
		//assert
		assertThat(actual).isSameAs(announcement);
		verify(announcementRepo).findOne(1L); 
	}

	@Test
	public void setUp_test_create_announcements() {
		// Arrange
		Announcement announcement = new Announcement();
		when(announcementRepo.save(announcement)).thenReturn(announcement);

		// act
		Announcement actual = controller.create(announcement);

		// assert
		assertThat(announcement).isSameAs(actual);
		verify(announcementRepo).save(announcement);
	}

	@Test
	public void setUp_test_delete_announcements() {
		// arrange
		Announcement announcement = new Announcement();
		when(announcementRepo.findOne(1L)).thenReturn(announcement);

		// act
		Announcement actual = controller.delete(1L);

		// assert
		assertThat(announcement).isSameAs(actual);
		verify(announcementRepo).delete(1L);

	}

	@Test
	public void test_announcement_is_saved_when_updated() {
			//arrange
			Announcement announcement = new Announcement(); 
			announcement.setId(1L);
			when(announcementRepo.save(announcement)).thenReturn(announcement); 
					
			//act
			Announcement actual = controller.update(announcement, 1L); 
					
			//assert
			assertThat(announcement).isSameAs(actual); 
			verify(announcementRepo).save(announcement); 
		}

	@Test
	public void test_delete_returns_announcement_deleted_when_found() {
		//arrange
		Announcement announcement = new Announcement(); 
		when(announcementRepo.findOne(2L)).thenReturn(announcement); 
		
		//act
		Announcement actual = controller.delete(1L); 
		
		//assert
		
	}

}
