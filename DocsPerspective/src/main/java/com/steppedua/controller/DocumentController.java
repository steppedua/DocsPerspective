package com.steppedua.controller;

import com.steppedua.domain.Document;
import com.steppedua.dto.DocumentDto;
import com.steppedua.mappers.DocumentMapper;
import com.steppedua.service.DocumentServiceImpl;
import com.steppedua.service.PageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentServiceImpl documentServiceImpl;
    private final PageServiceImpl pageServiceImpl;
    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentController(DocumentServiceImpl documentServiceImpl, PageServiceImpl pageServiceImpl, DocumentMapper documentMapper) {
        this.documentServiceImpl = documentServiceImpl;
        this.pageServiceImpl = pageServiceImpl;
        this.documentMapper = documentMapper;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Optional<Document>> uploadDocument(@RequestBody DocumentDto documentDto) {

        Optional<Document> newDocument = documentServiceImpl.uploadDocument(
                documentMapper.toCreateDocumentDto(documentDto)
        );

        return ResponseEntity.status(HttpStatus.OK).body(newDocument);
    }

    @DeleteMapping(value = "/delete/{documentId}/{userId}")
    public ResponseEntity<Optional<Document>> deleteDocument(@PathVariable(name = "documentId") String documentId,
                                                             @PathVariable(name = "userId") String userId
    ) {

        documentServiceImpl.deleteDocument(documentId, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/addDocumentPage/{documentId}/{userId}")
    public ResponseEntity<byte[]> addDocumentPage(@PathVariable(name = "documentId") String documentId,
                                                  @PathVariable(name = "userId") String userId
    ) throws IOException {

        byte[] newDocumentData = pageServiceImpl.addPage(documentId, userId);

        Document document = documentServiceImpl.changeFilePage(documentId, userId, newDocumentData);

        return ResponseEntity.status(HttpStatus.OK).body(document.getDocumentData());
    }

}
