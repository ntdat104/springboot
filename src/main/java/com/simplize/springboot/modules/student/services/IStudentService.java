package com.simplize.springboot.modules.student.services;

import org.springframework.http.ResponseEntity;

import com.simplize.springboot.models.ResponseObject;
import com.simplize.springboot.modules.student.entities.Student;

public interface IStudentService {
    ResponseEntity<ResponseObject> getStudents();
    ResponseEntity<ResponseObject> getStudentById(Long id);
    ResponseEntity<ResponseObject> addStudent(Student student);
    ResponseEntity<ResponseObject> updateStudent(Long id, Student student);
    ResponseEntity<ResponseObject> deleteStudent(Long id);
    ResponseEntity<ResponseObject> totalStudent();
}
