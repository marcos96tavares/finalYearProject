package com.example.LLM.Service.Imp;

import com.example.LLM.dto.ChatRequest;
import com.example.LLM.dto.UserDto;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        Long userId = Long.parseLong(chatRequest.userId());
        UserDto userDto = userApiCallService.searchForClient(userId);

        if (userDto == null) {
            throw new RuntimeException("User not found");
        }

        // Improved document creation with explicit user personal info formatting
        String content = String.format(
                "User Profile Information:\n" +
                        "- User ID: %d\n" +
                        "- Name: %s\n" +
                        "- Last Name: %s\n" +
                        "- Email: %s\n" +
                        "- Age: %d\n\n" +
                        "IMPORTANT: When responding to this user, always address them as %s and consider their age of %d years when providing training advice.",
                userDto.getUserDtoId(),
                userDto.getFirstNameDto(),
                userDto.getLastNameDto(),
                userDto.getEmailDto(),
                userDto.getAgeDto(),
                userDto.getFirstNameDto(),
                userDto.getAgeDto()
        );

        return Document.from(content);
    }

    public void loadDocuments(ChatRequest chatRequest) {
        Document document = createDocument(chatRequest);

        if (document == null) {
            System.err.println("❌ Failed to create document. User data might be missing.");
            return;
        }

        String userId = chatRequest.userId().toString();

        // Create metadata for the document
        Map<String, String> metadataMap = new HashMap<>();
        metadataMap.put("userId", userId);
        metadataMap.put("documentType", "userProfile");

        Metadata metadata = Metadata.from(metadataMap);
        document = Document.from(document.text(), metadata);

        System.out.println("📌 Ingesting document for USER_ID " + userId + ": " + document.text());

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(200, 10))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(document);

        System.out.println("✅ Stored Embeddings: " + embeddingStore.toString());
    }
}