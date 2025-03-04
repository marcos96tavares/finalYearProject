package com.example.LLM.Service;


import com.example.LLM.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "client-service", url = "http://localhost:8081")
public interface APiUserCall {

    @GetMapping("api/user/{id}")
    UserDto searchForClient(@PathVariable("id") Long id);


}
