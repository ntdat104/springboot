package com.simplize.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplize.springboot.dtos.StudentDTO;
import com.simplize.springboot.entities.Student;
import com.simplize.springboot.services.impl.StudentService;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping
    public List<StudentDTO> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{id}")
    public StudentDTO getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping(path = "{id}")
    public StudentDTO updateStudent(
            @PathVariable("id") Long id,
            @RequestBody Student student
        ) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping(path = "{id}")
    public StudentDTO deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }
}
