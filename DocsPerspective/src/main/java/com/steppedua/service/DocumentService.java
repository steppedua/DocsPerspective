package com.steppedua.service;

import com.steppedua.domain.Document;
import com.steppedua.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public Optional<Document> uploadDocument(Document document) {



        if (documentRepository.findByDocumentId(document.getDocumentId()).isPresent()) {
            return Optional.empty();
        }

        return Optional.of(documentRepository.saveAndFlush(document));
    }

    public Optional<Document> findDocumentById(String documentId) {
        return documentRepository.findByDocumentId(documentId);
    }


    public void deleteDocument(String documentId, String userId) {

        documentRepository.deleteDocumentByIdAndOwnerId(documentId, userId);
    }

}
