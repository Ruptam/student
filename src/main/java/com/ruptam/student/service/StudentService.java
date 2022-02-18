package com.ruptam.student.service;

import java.util.Optional;

import com.ruptam.student.entity.Student;
import com.ruptam.student.feignclients.AddressFeignClients;
import com.ruptam.student.model.AddressDTO;
import com.ruptam.student.model.StudentDTO;
import com.ruptam.student.repository.StudentRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

// import reactor.core.publisher.Mono;

@Service
public class StudentService {

    Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepo studentRepo;

    // @Autowired
    // private WebClient webClient;

    @Autowired
    private CommonService commonService;
    
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAddressId(studentDTO.getAddressId());
        Student savedStudent = studentRepo.save(student);
        StudentDTO studentDto = new StudentDTO();
        studentDto.setName(savedStudent.getName());
        // studentDto.setAddressDTO(getAddressById(savedStudent.getAddressId()));
        studentDto.setAddressDTO(commonService.getAddressById(savedStudent.getAddressId()));
        return studentDto;
    }

    public StudentDTO getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setAddressId(student.getAddressId());
            studentDTO.setAddressDTO(commonService.getAddressById(student.getAddressId()));
            return studentDTO;
        }
        return null;
    }

    // @CircuitBreaker(name = "addressService", fallbackMethod = "getAddressByIdFallBack")
    // private AddressDTO getAddressById(long addressId) {
    //     return addressFeignClients.getAddressById(addressId);
    // }

    // private AddressDTO getAddressByIdFallBack(long addressId, Throwable th) {
    //     log.info("Circuit breaker method triggered");
    //     return new AddressDTO();
    // }
}
