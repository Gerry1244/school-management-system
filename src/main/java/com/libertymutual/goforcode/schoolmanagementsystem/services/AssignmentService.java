package com.libertymutual.goforcode.schoolmanagementsystem.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.schoolmanagementsystem.dto.AssignmentDto;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Assignment;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Grade;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Student;
import com.libertymutual.goforcode.schoolmanagementsystem.models.Teacher;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.AssignmentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.GradeRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.StudentRepository;
import com.libertymutual.goforcode.schoolmanagementsystem.repositories.TeacherRepository;

@Service
public class AssignmentService {

	private AssignmentRepository assignmentRepo;
	private StudentRepository studentRepo;
	private TeacherRepository teacherRepo;
	private GradeRepository gradeRepo;

	public AssignmentService(AssignmentRepository assignmentRepo, StudentRepository studentRepo,
			TeacherRepository teacherRepo, GradeRepository gradeRepo) {
		this.assignmentRepo = assignmentRepo;
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
		this.gradeRepo = gradeRepo;

	}

	public Assignment create(String name, String description, Date dueDate, String comment, Long teacherId) {

		List<Student> students;
		try {
			Teacher teacher = teacherRepo.findOne(teacherId);
			students = studentRepo.findByTeacher(teacher);

			Assignment assignment = new Assignment(name, description, dueDate, comment, teacher);

			if (teacher != null && students != null) {
				assignment.setStudents(students);
				assignmentRepo.save(assignment);
				for (Student student : students) {
					Grade grade = new Grade();
					grade.setAssignment(assignment);
					grade.setStudent(student);
					grade.setLetterGradeValue("Not graded.");
					gradeRepo.save(grade);
				}

			}
			return assignment;

		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public Assignment delete(Long assignmentId) {
		try {
			Assignment assignment = assignmentRepo.findOne(assignmentId);
			for (Grade g : assignment.getGrades()) {
				g.setAssignment(null);
				gradeRepo.save(g);
			}
			assignmentRepo.delete(assignmentId);
			return assignment;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignment id: " + assignmentId + " not found. Error: " + erdae);
			return null;
		}

	}

	public Assignment update(String name, String description, Date dueDate, String comment, Long teacherId) {

		Assignment assignment = new Assignment(name, description, dueDate, comment);
		try {
			Teacher teacher = assignmentRepo.findOne(teacherId).getTeacher();
			assignment.setId(teacherId);
			assignment.setTeacher(teacher);
			assignmentRepo.save(assignment);
			return assignment;
		} catch (DataIntegrityViolationException dive) {
			System.err.println("Assignment in request body was not valid: " + dive);
			return null;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Teacher id: " + teacherId + " not found. Error: " + erdae);
			return null;
		}

	}

	public List<Assignment> getAllAssignmentsByTeacher(Long teacherId) {
		List<Assignment> assignments;
		try {
			Teacher teacher = teacherRepo.findOne(teacherId);
			assignments = assignmentRepo.findByTeacher(teacher);
			return assignments;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Teacher id: " + teacherId + " not found. Error: " + erdae);
			return null;
		}
	}

	public List<Assignment> getAllAssignmentsByStudent(Long studentId) {
		List<Assignment> assignments = new ArrayList<Assignment>();

		Student student = studentRepo.findOne(studentId);
		if (student != null) {
			List<Grade> grades = student.getGrades();
			for (Grade g : grades) {
				Assignment assignment = g.getAssignment();
				assignments.add(assignment);
			}
			return assignments;

		}
		return null;

	}

	public Assignment getOne(Long id) {
		try {
			Assignment assignment = assignmentRepo.findOne(id);
			return assignment;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignment id: " + id + " not found. Error: " + erdae);
			return null;
		}
	}

	public List<Assignment> getAll() {

		try {
			List<Assignment> assignments = assignmentRepo.findAll();
			return assignments;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("Assignments not found");
			return null;
		}
	}
}
