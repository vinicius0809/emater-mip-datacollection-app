package br.edu.utfpr.cp.emater.midmipsystem.domain.base;
import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Authority extends AuditingPersistenceEntity implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    private String authority;
}
