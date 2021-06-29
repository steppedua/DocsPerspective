package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "attributes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attributes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("attribute_id")
    private Long attributeId;  // Уникальный идентификатор атрибута

    @NotEmpty
    @Size(min = 3, max = 15)
    @Column(name = "attribute_name", unique = true, nullable = false)
    @JsonProperty("attribute_name")
    private String attributeName;  // Название атрибута

    // У каждого документа может быть несколько атрибутов
    @JsonIgnore
    @ManyToMany(mappedBy = "documentAttributes", fetch = FetchType.LAZY)
    private Set<Document> documents = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attributes that = (Attributes) o;
        return Objects.equals(attributeId, that.attributeId) && Objects.equals(attributeName, that.attributeName) && Objects.equals(documents, that.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeId, attributeName, documents);
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "attributeId=" + attributeId +
                ", attributeName='" + attributeName + '\'' +
                ", documents=" + documents +
                '}';
    }
}