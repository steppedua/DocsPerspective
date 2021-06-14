package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "document_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentData implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "document_data_id")
    private String documentDataId;

    @Lob
    @NotEmpty
    @Column(name = "document_data")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] documentData;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "documentData")
    private Document document;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentData that = (DocumentData) o;
        return Objects.equals(documentDataId, that.documentDataId) && Objects.equals(documentData, that.documentData) && Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentDataId, documentData, document);
    }

    @Override
    public String toString() {
        return "DocumentData{" +
                "documentDataId='" + documentDataId + '\'' +
                ", documentData='" + documentData + '\'' +
                ", document=" + document +
                '}';
    }
}
