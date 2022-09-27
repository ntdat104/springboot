package com.simplize.springboot.student.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplize.springboot.student.converters.impl.StudentConverter;
import com.simplize.springboot.student.dtos.StudentDTO;
import com.simplize.springboot.student.entities.Student;
import com.simplize.springboot.student.repositoies.StudentRepository;
import com.simplize.springboot.student.services.IStudentService;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    private final StudentConverter studentConverter;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
    }
    
    public List<StudentDTO> getStudents() {
        List<StudentDTO> dtos = new ArrayList<>();
        List<Student> entities = studentRepository.findAll();
        for (Student entity: entities) {
			StudentDTO dto = studentConverter.toDTO(entity);
			dtos.add(dto);
		}
        return dtos;
    }

    public StudentDTO getStudentById(Long id) {
        boolean isExisted = studentRepository.existsById(id);

        if(!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }

        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();
        StudentDTO dto = studentConverter.toDTO(studentWithId);

        return dto;
    }

    public StudentDTO updateStudent(Long id, Student student) {
        boolean isExisted = studentRepository.existsById(id);

        if(!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }

        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();
        if (student.getName() != null) {
            studentWithId.setName(student.getName());
        }
        if (student.getDateOfBirth() != null) {
            studentWithId.setDateOfBirth(student.getDateOfBirth());
        }
        if (student.getEmail() != null) {
            Optional<Student> studentOptionalEmail = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptionalEmail.isPresent()) {
                throw new IllegalStateException("Email has been taken!");
            }
            studentWithId.setEmail(student.getEmail());
        }

        StudentDTO dto = studentConverter.toDTO(studentWithId);

        return dto;
    }

    public StudentDTO addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email has been taken!");
        }
        Student entity = studentRepository.save(student);
        StudentDTO dto = studentConverter.toDTO(entity);

        return dto;
    }

    public StudentDTO deleteStudent(Long id) {
        boolean isExisted = studentRepository.existsById(id);
        if (!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        };
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();
        StudentDTO dto = studentConverter.toDTO(studentWithId);
        studentRepository.deleteById(id);
        return dto;
    }

    public Long totalStudent() {
        return studentRepository.count();
    }
}
