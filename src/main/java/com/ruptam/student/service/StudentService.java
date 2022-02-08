package com.ruptam.student.service;

import java.util.Optional;

import com.ruptam.student.entity.Student;
import com.ruptam.student.model.AddressDTO;
import com.ruptam.student.model.StudentDTO;
import com.ruptam.student.repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private WebClient webClient;
    
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAddressId(studentDTO.getAddressId());
        Student savedStudent = studentRepo.save(student);
        StudentDTO studentDto = new StudentDTO();
        studentDto.setName(savedStudent.getName());
        studentDto.setAddressDTO(getAddressById(savedStudent.getAddressId()));
        return studentDto;
    }

    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        return null;
    }

    private AddressDTO getAddressById(long addressId) {
      Mono<AddressDTO> addressResponseEntity =
         webClient.get().uri("?addressId=" + addressId).retrieve().bodyToMono(AddressDTO.class);

        return addressResponseEntity.block();
    }
}
