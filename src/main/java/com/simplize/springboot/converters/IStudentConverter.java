package com.simplize.springboot.converters;

import com.simplize.springboot.dtos.StudentDTO;
import com.simplize.springboot.entities.Student;

public interface IStudentConverter {
    Student toEntity(StudentDTO dto);
    Student toEntity(StudentDTO dto, Student entity);
    StudentDTO toDTO(Student entity);
}
