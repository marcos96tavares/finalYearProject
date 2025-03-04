package com.example.LLM.Controller;

import com.example.LLM.Service.GenAIService;
import com.example.LLM.Service.Imp.EmbeddingComponet;
import com.example.LLM.dto.ChatRequest;
import com.example.LLM.dto.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class GenerativeController {


    private  final GenAIService genAIService;
    private final EmbeddingComponet embeddingComponet;

    public GenerativeController(GenAIService genAIService, EmbeddingComponet embeddingComponet) {
        this.genAIService = genAIService;
        this.embeddingComponet = embeddingComponet;
    }





    @PostMapping
    public ChatResponse getExtanded(@RequestBody ChatRequest request) {
        embeddingComponet.loadDocuments(request);
        return new ChatResponse(genAIService.getResponseExtended(request));
    }



}
