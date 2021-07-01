package com.steppedua.service;

import com.steppedua.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PageServiceImpl {

    private final DocumentRepository documentRepository;

    @Value("filePath")
    private String filePath;

    public byte[] addPage(String documentId, String userId) throws IOException {
        // Получаем документ в двоичной кодировке формата Base64
        byte[] documentData = documentRepository.getDataOnDocumentIdAndOwnerId(documentId, userId);

        // Получаем имя документа
        String documentName = documentRepository.getDocumentNameOnDocumentIdAndOwnerId(documentId, userId);

        File file = new File(filePath + File.separator + documentName);

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
