package com.simplize.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplize.springboot.entities.Student;
import com.simplize.springboot.repositoies.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        boolean isExisted = studentRepository.existsById(id);

        if(!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }

        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();

        return studentWithId;
    }

    public Student updateStudent(Long id, Student student) {
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

        return studentRepository.save(studentWithId);
    }

    public Student addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email has been taken!");
        }

        return studentRepository.save(student);
    }

    public Student deleteStudent(Long id) {
        boolean isExisted = studentRepository.existsById(id);
        if (!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        };
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();
        studentRepository.deleteById(id);
        return studentWithId;
    }
}
