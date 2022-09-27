package com.simplize.springboot.student.converters;

import com.simplize.springboot.student.dtos.StudentDTO;
import com.simplize.springboot.student.entities.Student;

public interface IStudentConverter {
    Student toEntity(StudentDTO dto);
    Student toEntity(StudentDTO dto, Student entity);
    StudentDTO toDTO(Student entity);
}
