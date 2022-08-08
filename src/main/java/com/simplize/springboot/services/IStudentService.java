package com.simplize.springboot.services;

import java.util.List;

import com.simplize.springboot.dtos.StudentDTO;
import com.simplize.springboot.entities.Student;

public interface IStudentService {
    List<StudentDTO> getStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO addStudent(Student student);
    StudentDTO updateStudent(Long id, Student student);
    StudentDTO deleteStudent(Long id);
    Long totalStudent();
}
