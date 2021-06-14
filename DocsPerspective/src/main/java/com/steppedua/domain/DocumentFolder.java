package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "documents_folder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentFolder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_folder_id")
    private Long documentsFolderId;

    @NotEmpty
    @Column(name = "document_folder_name")
    @Size(min = 4, max = 15)
    private String documentsFolderName;

    @JsonIgnore
    @OneToMany(mappedBy = "documentFolder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Document> documents = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentFolder that = (DocumentFolder) o;
        return Objects.equals(documentsFolderId, that.documentsFolderId) && Objects.equals(documentsFolderName, that.documentsFolderName) && Objects.equals(documents, that.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentsFolderId, documentsFolderName, documents);
    }

    @Override
    public String toString() {
        return "DocumentFolder{" +
                "documentsFolderId=" + documentsFolderId +
                ", documentsFolderName='" + documentsFolderName + '\'' +
                ", documents=" + documents +
                '}';
    }
}
