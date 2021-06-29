package com.steppedua.controller;

import com.steppedua.domain.Document;
import com.steppedua.dto.DocumentDto;
import com.steppedua.mappers.DocumentMapper;
import com.steppedua.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentServiceImpl;
    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentController(DocumentService documentServiceImpl, DocumentMapper documentMapper) {
        this.documentServiceImpl = documentServiceImpl;
        this.documentMapper = documentMapper;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Optional<Document>> createDocument(@RequestBody DocumentDto documentDto) {

        Optional<Document> newDocument = documentServiceImpl.uploadDocument(
                documentMapper.toCreateDocumentDto(documentDto)
        );

        return ResponseEntity.status(HttpStatus.OK).body(newDocument);
    }

    @DeleteMapping(value = "/delete/{documentId}/{userId}")
    public ResponseEntity<Optional<Document>> deleteDocument(@PathVariable(name = "documentId") String documentId, @PathVariable(name = "userId") String userId) {

        documentServiceImpl.deleteDocument(documentId, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
