package com.example.LLM.Service.Imp;

import com.example.LLM.Service.Assistant;
import com.example.LLM.Service.GenAIService;
import com.example.LLM.Service.RAGAssistant;
import com.example.LLM.dto.ChatRequest;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;


@Service
public class GenAIServiceImp implements GenAIService {

 private final Assistant assistant;
 private final RAGAssistant ragAssistant;

    public GenAIServiceImp(Assistant assistant, RAGAssistant ragAssistant) {
        this.assistant = assistant;
        this.ragAssistant = ragAssistant;
    }


    @Override
    public String getResponse(ChatRequest request) {
      return assistant.chat(request.userId(), request.message());
    }

    @Override
    public String getResponseExtended(ChatRequest request) {
        return ragAssistant.chat(request.userId(), request.message());
    }
}
