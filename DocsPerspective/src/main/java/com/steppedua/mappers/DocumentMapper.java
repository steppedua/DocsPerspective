package com.steppedua.mappers;

import com.steppedua.domain.Document;
import com.steppedua.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface DocumentMapper {
    @Mappings({
            @Mapping(target = "documentId", source = "document.documentId"),
            @Mapping(target = "documentName", source = "document.documentName"),
            @Mapping(target = "creationDate", source = "document.creationDate"),
            @Mapping(target = "documentType", source = "document.documentType"),
            @Mapping(target = "documentFolder", source = "document.documentFolder"),
            @Mapping(target = "documentAttributes", source = "document.documentAttributes"),
            @Mapping(target = "documentData", source = "document.documentData")
    })
    DocumentDto toDocumentDto(Document document);

    @Mappings({
            @Mapping(target = "documentId", source = "documentDto.documentId"),
            @Mapping(target = "documentName", source = "documentDto.documentName"),
            @Mapping(target = "creationDate", source = "documentDto.creationDate"),
            @Mapping(target = "owner", source = "documentDto.owner"),
            @Mapping(target = "documentType", source = "documentDto.documentType"),
            @Mapping(target = "documentFolder", source = "documentDto.documentFolder"),
            @Mapping(target = "documentAttributes", source = "documentDto.documentAttributes"),
            @Mapping(target = "documentData", source = "documentDto.documentData")
    })
    default Document toCreateDocumentDto(DocumentDto documentDto) {

        return new Document(
                documentDto.getDocumentName(),
                documentDto.getCreationDate(),
                documentDto.getDocumentType(),
                documentDto.getOwner(),
                documentDto.getDocumentFolder(),
                documentDto.getDocumentAttributes(),
                documentDto.getDocumentData()
        );
    }

    @Mappings({
            @Mapping(target = "documentId", source = "document.documentId"),
            @Mapping(target = "documentName", source = "document.documentName"),
            @Mapping(target = "creationDate", source = "document.creationDate"),
            @Mapping(target = "documentType", source = "document.documentType"),
            @Mapping(target = "documentFolder", source = "document.documentFolder"),
            @Mapping(target = "documentAttributes", source = "document.documentAttributes"),
            @Mapping(target = "documentData", source = "document.documentData")
    })
    List<DocumentDto> toDocumentsListByOwnerIdDto(List<Document> userDocumentsList);
}
