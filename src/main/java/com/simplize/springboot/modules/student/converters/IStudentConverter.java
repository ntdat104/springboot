package com.simplize.springboot.modules.student.converters;

import com.simplize.springboot.modules.student.dtos.StudentDTO;
import com.simplize.springboot.modules.student.entities.Student;

public interface IStudentConverter {
    Student toEntity(StudentDTO dto);
    Student toEntity(StudentDTO dto, Student entity);
    StudentDTO toDTO(Student entity);
}
