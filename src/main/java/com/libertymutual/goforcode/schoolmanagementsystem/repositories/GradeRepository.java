package com.libertymutual.goforcode.schoolmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
