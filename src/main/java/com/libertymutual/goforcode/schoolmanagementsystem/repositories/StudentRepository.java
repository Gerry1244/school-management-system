package com.libertymutual.goforcode.schoolmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
