package com.ruptam.student.service;

import com.ruptam.student.feignclients.AddressFeignClients;
import com.ruptam.student.model.AddressDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {

    private static final Logger log = LoggerFactory.getLogger(CommonService.class);
    Long count = 1l;
    
    @Autowired
    private AddressFeignClients addressFeignClients;

    @CircuitBreaker(name = "addressService", fallbackMethod = "getAddressByIdFallBack")
    public AddressDTO getAddressById(long addressId) {
        log.info("Count ==> " + count++);
        return addressFeignClients.getAddressById(addressId);
    }

    private AddressDTO getAddressByIdFallBack(long addressId, Throwable th) {
        log.info("Circuit breaker method triggered");
        log.error("error => " + th);
        return new AddressDTO();
    }
}
