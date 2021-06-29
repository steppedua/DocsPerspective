package com.steppedua.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.steppedua.domain.Attributes;
import com.steppedua.domain.DocumentFolder;
import com.steppedua.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto implements Serializable {

    @JsonProperty("document_id")
    private String documentId;

    @JsonProperty("owner")
    private User owner;

    @JsonProperty("document_name")
    private String documentName;

    @JsonProperty("creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @JsonProperty("document_type")
    private String documentType;

    @JsonProperty("document_folder")
    private DocumentFolder documentFolder;

    @JsonProperty("document_attributes")
    private Set<Attributes> documentAttributes;

    @JsonProperty("document_data")
    private byte[] documentData;
}