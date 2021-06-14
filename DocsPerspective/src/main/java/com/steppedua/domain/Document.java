package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

    @NotEmpty
    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

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
    private Set<Attributes> documentAttributes = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_data_id", nullable = false)
    private DocumentData documentData;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId) && Objects.equals(documentName, document.documentName) && Objects.equals(creationDate, document.creationDate) && Objects.equals(owner, document.owner) && Objects.equals(documentType, document.documentType) && Objects.equals(documentFolder, document.documentFolder) && Objects.equals(documentAttributes, document.documentAttributes) && Objects.equals(documentData, document.documentData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, documentName, creationDate, owner, documentType, documentFolder, documentAttributes, documentData);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", documentName='" + documentName + '\'' +
                ", creationDate=" + creationDate +
                ", owner=" + owner +
                ", documentType=" + documentType +
                ", documentFolder=" + documentFolder +
                ", documentAttributes=" + documentAttributes +
                ", documentData=" + documentData +
                '}';
    }
}
