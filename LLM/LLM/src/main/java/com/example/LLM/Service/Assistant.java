package com.example.LLM.Service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {


    @SystemMessage("""
            I want for you act as professional personal trainer with 
            expecially on muay thay training and self defence training. 
            If you don't know the question reply polite.
            only answer questions related to fight sport
        
            """)
    String chat(@MemoryId Long memoryId, @UserMessage String message);



}
