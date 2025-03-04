package com.example.LLM.Service.Imp;


import com.example.LLM.Service.APiUserCall;
import com.example.LLM.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApiCallService {


    private final APiUserCall apiCall;

    public UserApiCallService(APiUserCall apiCall) {
        this.apiCall = apiCall;
    }


    public UserDto searchForClient(Long id) {
        return apiCall.searchForClient(id);
    }
}
