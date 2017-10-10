package com.libertymutual.goforcode.schoolmanagementsystem.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Announcement;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AnnouncementRepository;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementApiController {
	
	private AnnouncementRepository announcementRepo;
	
	public AnnouncementApiController(AnnouncementRepository announcementRepo) {
		this.announcementRepo = announcementRepo;
	}
	
	@GetMapping("")
	public List<Announcement> getAll() {
		return announcementRepo.findAll();
	}
	
	@PostMapping("")
	public Announcement create(@RequestBody Announcement announcement) {
		return announcementRepo.save(announcement);
	}
	
	@DeleteMapping("{id}")
	public Announcement delete(@PathVariable long id) {
		try {
			Announcement announcement = announcementRepo.findOne(id);
			announcementRepo.delete(id);
			return announcement;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	@PutMapping("{id}")
	public Announcement update(@RequestBody Announcement announcement, @PathVariable long id) {
		announcement.setId(id);
		return announcementRepo.save(announcement);
	}
	
}
