package com.example.LLM.Config;

import com.example.LLM.Service.Assistant;
import com.example.LLM.Service.RAGAssistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModelName;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@Configuration
public class Config {

    @Value("${Api-key}")
    private  String KEY;


    @Bean
    public ChatLanguageModel chatLanguageModel() {

        return OpenAiChatModel.builder()
                .apiKey(KEY)
                .modelName(GPT_4_O_MINI)
                .build();


    }


    @Bean
    public Assistant assistant() {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(chatLanguageModel())
                .chatMemoryProvider(x-> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    @Bean
    public EmbeddingModel embeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(KEY)
                .modelName(OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_SMALL)
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }



    @Bean
    public RAGAssistant ragAssistant() {
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore())
                .embeddingModel(embeddingModel())
                .maxResults(10)
                .minScore(0.6) // Adjusted for better retrieval
                .build();

        return AiServices.builder(RAGAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .contentRetriever(query -> {
                    var results = contentRetriever.retrieve(query);
                    System.out.println("Retrieved Documents: " + results);
                    return results;
                })
                .chatMemoryProvider(x -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }





}