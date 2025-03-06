package com.example.LLM.Service.Imp;


import com.example.LLM.Service.APiUserCall;
import com.example.LLM.dto.ChatRequest;
import com.example.LLM.dto.UserDto;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;

import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import dev.langchain4j.data.document.Document;

import java.util.Map;

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







 // ✅ Import Metadata

    public void loadDocuments(ChatRequest chatRequest) {
        Document document = createDocument(chatRequest);

        if (document == null) {
            System.err.println("❌ Failed to create document. User data might be missing.");
            return;
        }

        String userId = chatRequest.userId().toString();

        // ✅ Convert Map to Metadata
        Metadata metadata = Metadata.from(Map.of("ID", userId));

        // ✅ Create document with metadata
        document = Document.from(document.text(), metadata);

        System.out.println("📌 Ingesting document for USER_ID " + userId + ": " + document.text());

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentTransformer(document1 -> {
                    document1.metadata().put("id", String.valueOf(chatRequest.userId())); // Use lowercase "id"
                    return document1;
                })
                .documentSplitter(DocumentSplitters.recursive(200, 10))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();


        ingestor.ingest(document);

        System.out.println("✅ Stored Embeddings: " + embeddingStore.toString());
    }














}