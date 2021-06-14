package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "document_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentType {
    //CONTRACT, AGREEMENT, ORDER, INSTRUCTION;
    // Контракт/Договор, Соглашение, Заказ от подрядчиков или собственный заказ, Инструкция

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documents_type_id")
    private Long documentsFolderId;

    @NotEmpty
    @Size(min = 4, max = 15)
    @Column(name = "documents_type_name")
    private String documentsFolderName;

    @JsonIgnore
    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Document> documents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentType that = (DocumentType) o;
        return Objects.equals(documentsFolderId, that.documentsFolderId) && Objects.equals(documentsFolderName, that.documentsFolderName) && Objects.equals(documents, that.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentsFolderId, documentsFolderName, documents);
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "documentsFolderId=" + documentsFolderId +
                ", documentsFolderName='" + documentsFolderName + '\'' +
                ", documents=" + documents +
                '}';
    }
}
