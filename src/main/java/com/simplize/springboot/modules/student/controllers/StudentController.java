package com.simplize.springboot.modules.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplize.springboot.models.ResponseObject;
import com.simplize.springboot.modules.student.entities.Student;
import com.simplize.springboot.modules.student.services.impl.StudentService;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping
    public ResponseEntity<ResponseObject> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseObject> getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ResponseObject> updateStudent(
            @PathVariable("id") Long id,
            @RequestBody Student student
        ) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ResponseObject> deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }
}
