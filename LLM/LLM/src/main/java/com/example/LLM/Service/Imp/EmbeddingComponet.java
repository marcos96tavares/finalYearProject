package com.example.LLM.Service.Imp;


import com.example.LLM.Service.APiUserCall;
import com.example.LLM.dto.ChatRequest;
import com.example.LLM.dto.UserDto;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import dev.langchain4j.data.document.Document;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;


@Component

public class EmbeddingComponet {


    private UserApiCallService userApiCallService;
    private EmbeddingModel embeddingModel;
    private EmbeddingStore embeddingStore;

    public EmbeddingComponet(UserApiCallService userApiCallService, EmbeddingModel embeddingModel, EmbeddingStore embeddingStore) {
        this.userApiCallService = userApiCallService;
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    private Document createDocument(ChatRequest chatRequest) {

        Long userId = chatRequest.userId();
        UserDto userDto = userApiCallService.searchForClient(userId);

        if (userDto == null) {
            throw new RuntimeException("User not found");
        }

        String content = String.format(
                "User Profile:\n- ID: %d\n- Name: %s\n- Email: %s\n- LastName: %s",
                userDto.getUserDtoId(),
                userDto.getFirstNameDto(),
                userDto.getEmailDto(),
                userDto.getLastNameDto() // Add more fields if needed
        );
        return Document.from(content);

    }







    public void loadDocuments(ChatRequest chatRequest) {
        Document document = createDocument(chatRequest);

        // Print document before ingesting
        System.out.println("Ingesting document: " + document.text());


        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(200, 10))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(document);

        // Print stored embeddings for debugging
        System.out.println("Stored Embeddings: " + embeddingStore.toString());
    }











}
