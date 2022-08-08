package com.simplize.springboot.converters.impl;

import org.springframework.stereotype.Component;

import com.simplize.springboot.converters.IStudentConverter;
import com.simplize.springboot.dtos.StudentDTO;
import com.simplize.springboot.entities.Student;

@Component
public class StudentConverter implements IStudentConverter {
    
    public Student toEntity(StudentDTO dto) {
        Student entity = new Student();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setDateOfBirth(dto.getDateOfBirth());
        return entity;
    }

    public Student toEntity(StudentDTO dto, Student entity) {
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setEmail(dto.getEmail());
		entity.setDateOfBirth(dto.getDateOfBirth());
		return entity;
	}

    public StudentDTO toDTO(Student entity) {
		StudentDTO dto = new StudentDTO();
        if(entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        dto.setDateOfBirth(entity.getDateOfBirth());
        return dto;
	}
}
