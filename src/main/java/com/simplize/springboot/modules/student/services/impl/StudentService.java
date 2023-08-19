package com.simplize.springboot.modules.student.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.simplize.springboot.models.ResponseObject;
import com.simplize.springboot.modules.student.converters.impl.StudentConverter;
import com.simplize.springboot.modules.student.dtos.StudentDTO;
import com.simplize.springboot.modules.student.entities.Student;
import com.simplize.springboot.modules.student.repositoies.StudentRepository;
import com.simplize.springboot.modules.student.services.IStudentService;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter studentConverter;

    // @Autowired
    // public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
    //     this.studentRepository = studentRepository;
    //     this.studentConverter = studentConverter;
    // }
    
    public ResponseEntity<ResponseObject> getStudents() {
        List<StudentDTO> dtos = new ArrayList<>();
        List<Student> entities = studentRepository.findAll();
        for (Student entity: entities) {
			StudentDTO dto = studentConverter.toDTO(entity);
			dtos.add(dto);
		}
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "getStudents successfully", dtos)
        );
    }

    public ResponseEntity<ResponseObject> getStudentById(Long id) {
        boolean isExisted = studentRepository.existsById(id);

        if(!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }

        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();
        StudentDTO dto = studentConverter.toDTO(studentWithId);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "getStudentById successfully", dto)
        );
    }

    public ResponseEntity<ResponseObject> updateStudent(Long id, Student student) {
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

        Student entity = studentRepository.save(studentWithId);

        StudentDTO dto = studentConverter.toDTO(entity);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "updateStudent successfully", dto)
        );
    }

    public ResponseEntity<ResponseObject> addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email has been taken!");
        }
        Student entity = studentRepository.save(student);
        StudentDTO dto = studentConverter.toDTO(entity);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "addStudent successfully", dto)
        );
    }

    public ResponseEntity<ResponseObject> deleteStudent(Long id) {
        Boolean isExisted = studentRepository.existsById(id);
        if (!isExisted) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        };
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentWithId = studentOptional.get();
        StudentDTO dto = studentConverter.toDTO(studentWithId);
        studentRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "deleteStudent successfully", dto)
        );
    }

    public ResponseEntity<ResponseObject> totalStudent() {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "totalStudent successfully", studentRepository.count())
        );
    }
}
