package com.steppedua.service;

import com.steppedua.domain.Document;
import com.steppedua.repository.DocumentRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
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

    public byte[] addPage(String documentId, String userId) throws IOException {
        // Получаем документ в двоичной кодировке формата Base64
        byte[] documentData = documentRepository.getDataOnDocumentIdAndOwnerId(documentId, userId);

        // Получаем имя документа
        String documentName = documentRepository.getDocumentNameOnDocumentIdAndOwnerId(documentId, userId);

        File file = new File("/Users/edstepa/Desktop/Диплом/DocsPerspective/src/main/resources/documents/" + documentName);

        // Тут декодируем файл из БД
        byte[] decodeDocumentDataFromDB = Base64.getDecoder().decode(documentData);

        PDDocument pdDocument = PDDocument.load(decodeDocumentDataFromDB);

        // Создаем новую страницу
        PDPage blankPage = new PDPage();
        pdDocument.addPage(blankPage);

        pdDocument.save(file);
        pdDocument.close();

        // Делаем запись в массив байтов отредактированный документ
        byte[] readAllBytesFromFile = Files.readAllBytes(file.toPath());

        // Кодируем файл, а потом засовываем в БД
        byte[] encodeFile = Base64.getEncoder().encode(readAllBytesFromFile);

        return encodeFile;
    }

}
