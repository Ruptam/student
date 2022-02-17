package com.ruptam.student.feignclients;

import com.ruptam.student.model.AddressDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-gateway")
public interface AddressFeignClients {


    @GetMapping(value = "/address-service/address/")
    public AddressDTO getAddressById(@RequestParam("addressId") Long addressId);
    
}
