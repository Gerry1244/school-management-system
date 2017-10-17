package com.libertymutual.goforcode.schoolmanagementsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	List<Teacher> findByGradeLevel(int gradeLevel);
}
