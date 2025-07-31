package com.example.LLM.Service.Imp;

import com.example.LLM.Service.GenAIService;
import com.example.LLM.Service.RAGAssistant;
import com.example.LLM.dto.ChatRequest;
import com.example.LLM.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class GenAIServiceImp implements GenAIService {

    private final RAGAssistant ragAssistant;
    private final UserApiCallService userApiCallService;

    public GenAIServiceImp(RAGAssistant ragAssistant, UserApiCallService userApiCallService) {
        this.ragAssistant = ragAssistant;
        this.userApiCallService = userApiCallService;
    }

    @Override
    public String getResponse(ChatRequest request) {
        // Format the message to include user details
        UserDto user = userApiCallService.searchForClient(Long.parseLong(request.userId()));

        if (user == null) {
            return "I'm sorry, I couldn't retrieve your information. Please try again.";
        }

        // Create a personalized prefix for the message
        String personalizedPrefix = String.format(
                "The user's name is %s and they are %d years old. Remember to address them personally as %s and reference their age %d when appropriate. Here's their question: ",
                user.getFirstNameDto(),
                user.getAgeDto(),
                user.getFirstNameDto(),
                user.getAgeDto()
        );

        // Combine the prefix with the original message
        String enhancedMessage = personalizedPrefix + request.message();

        return ragAssistant.chat(Long.parseLong(request.userId()), enhancedMessage);
    }

    @Override
    public String getResponseExtended(ChatRequest request) {
        UserDto user = userApiCallService.searchForClient(Long.parseLong(request.userId()));

        if (user == null) {
            return "I'm sorry, I couldn't retrieve your information. Please try again.";
        }

        // Create a personalized message with user details
        String personalizedMessage = String.format(
                "My name is %s and I am %d years old. %s",
                user.getFirstNameDto(),
                user.getAgeDto(),
                request.message()
        );

        return ragAssistant.chat(Long.parseLong(request.userId()), personalizedMessage);
    }
}