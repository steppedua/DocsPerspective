package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "document_id")
    private String documentId;

    @NotEmpty
    @Size(min = 4, max = 15)
    @Column(name = "document_name")
    private String documentName;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @Size(min = 4, max = 20)
    @Column(name = "document_type")
    private String documentType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "document_folder_id")
    private DocumentFolder documentFolder;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "document_attributes",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attributes> documentAttributes;

    @Lob
    @NotEmpty
    @Column(name = "document_data")
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonProperty("data")
    private byte[] documentData;

    public Document(String documentName, Date creationDate, String documentType, User owner, DocumentFolder documentFolder, Set<Attributes> documentAttributes, @NotEmpty byte[] documentData) {
        this.documentName = documentName;
        this.creationDate = creationDate;
        this.documentType = documentType;
        this.owner = owner;
        this.documentFolder = documentFolder;
        this.documentAttributes = documentAttributes;
        this.documentData = documentData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId) && Objects.equals(documentName, document.documentName) && Objects.equals(creationDate, document.creationDate) && Objects.equals(documentType, document.documentType) && Objects.equals(owner, document.owner) && Objects.equals(documentFolder, document.documentFolder) && Objects.equals(documentAttributes, document.documentAttributes) && Arrays.equals(documentData, document.documentData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(documentId, documentName, creationDate, documentType, owner, documentFolder, documentAttributes);
        result = 31 * result + Arrays.hashCode(documentData);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", documentName='" + documentName + '\'' +
                ", creationDate=" + creationDate +
                ", documentType='" + documentType + '\'' +
                ", owner=" + owner +
                ", documentFolder=" + documentFolder +
                ", documentAttributes=" + documentAttributes +
                ", documentData=" + Arrays.toString(documentData) +
                '}';
    }
}
