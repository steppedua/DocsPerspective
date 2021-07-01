package com.steppedua.repository;

import com.steppedua.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

    @Transactional
    @Query("SELECT u FROM Document u WHERE u.documentId= :documentId")
    Optional<Document> findByDocumentId(@Param("documentId") String documentId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Document u WHERE u.documentId= :documentId AND u.owner.userId = :userId")
    void deleteDocumentByIdAndOwnerId(@Param("documentId") String documentId, @Param("userId") String ownerId);

    @Transactional
    @Query("SELECT u.documentData FROM Document u WHERE u.documentId= :documentId AND u.owner.userId = :userId")
    byte[] getDataOnDocumentIdAndOwnerId(@Param("documentId") String documentId, @Param("userId") String ownerId);

    @Transactional
    @Query("SELECT u FROM Document u WHERE u.documentId= :documentId AND u.owner.userId = :userId")
    Optional<Document> findByDocumentIdAndOwnerId(@Param("documentId") String documentId, @Param("userId") String ownerId);

    @Transactional
    @Query("SELECT u.documentName FROM Document u WHERE u.documentId= :documentId AND u.owner.userId = :userId")
    String getDocumentNameOnDocumentIdAndOwnerId(String documentId, String userId);
}
