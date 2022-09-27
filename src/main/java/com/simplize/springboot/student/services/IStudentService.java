package com.simplize.springboot.student.services;

import java.util.List;

import com.simplize.springboot.student.dtos.StudentDTO;
import com.simplize.springboot.student.entities.Student;

public interface IStudentService {
    List<StudentDTO> getStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO addStudent(Student student);
    StudentDTO updateStudent(Long id, Student student);
    StudentDTO deleteStudent(Long id);
    Long totalStudent();
}
