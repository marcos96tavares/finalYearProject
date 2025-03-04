package com.example.LLM.Service;

import com.example.LLM.dto.ChatRequest;

public interface GenAIService {

    String getResponse(ChatRequest request);

    String getResponseExtended(ChatRequest request);
}
