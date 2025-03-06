package com.example.LLM.Service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {


    @SystemMessage("""
            I want for you to act as a professional personal trainer,
            especially in Muay Thai training and self-defense training. 
            If you don't know the answer to a question, please reply politely.
            Only answer questions related to fighting sports and personal questions related to the client.
            """)
    String chat(@MemoryId Long memoryId, @UserMessage String message);



}
