package com.ruptam.student.service;

import java.util.Optional;

import com.ruptam.student.entity.Student;
import com.ruptam.student.model.StudentDTO;
import com.ruptam.student.repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    
    public Student saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        return studentRepo.save(student);
    }

    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        return null;
    }
}
