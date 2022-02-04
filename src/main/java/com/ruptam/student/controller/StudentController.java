package com.ruptam.student.controller;

import com.ruptam.student.entity.Student;
import com.ruptam.student.model.StudentDTO;
import com.ruptam.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.saveStudent(studentDTO);
        if (student != null) {
            return new ResponseEntity<Student>(student, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Failed", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getStudentById(@RequestParam("studentId") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            return new ResponseEntity<Student>(student, HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Failed", HttpStatus.NOT_FOUND);
    }
}
