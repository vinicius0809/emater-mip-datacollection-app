package br.edu.utfpr.cp.emater.midmipsystem.library;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners (AuditingEntityListener.class)
public class AuditingPersistenceEntity {

    @CreatedDate
    private Long createdAt;

    @LastModifiedDate
    private Long lastModified;
}