package com.steppedua.service;

import com.steppedua.domain.Document;
import com.steppedua.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl {
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

    public byte[] getDocumentData(String documentId, String userId) {
        return documentRepository.getDataOnDocumentIdAndOwnerId(documentId, userId);
    }

    public String getDocumentNameByIdAndOwnerId(String documentId, String userId){
        return documentRepository.getDocumentNameOnDocumentIdAndOwnerId(documentId, userId);
    }


    public Document changeFilePage(String documentId, String userId, byte[] data) {


        Optional<Document> byDocumentIdAndOwnerId = documentRepository.findByDocumentIdAndOwnerId(documentId, userId);
        byDocumentIdAndOwnerId.get().setDocumentData(data);

        return documentRepository.save(byDocumentIdAndOwnerId.get());
    }
}
