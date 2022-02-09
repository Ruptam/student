package com.ruptam.student.feignclients;

import com.ruptam.student.model.AddressDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${address.application.url}", value = "address-feign-client", path = "/address")
public interface AddressFeignClients {


    @GetMapping(value = "/")
    public AddressDTO getAddressById(@RequestParam("addressId") Long addressId);
    
}
