package com.example.Client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin-service", url = "http://localhost:8080/api/booking")
public interface ApiBookingCall {

    @DeleteMapping("/delete/{id}")
    Boolean deleteBooking(@PathVariable("id") Long id);


}
