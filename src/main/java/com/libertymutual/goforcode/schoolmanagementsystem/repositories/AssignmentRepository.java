package com.libertymutual.goforcode.schoolmanagementsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

	List<Assignment> findByTeacher(Teacher teacher);
}
