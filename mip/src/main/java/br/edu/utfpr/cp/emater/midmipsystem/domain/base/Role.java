package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Role extends AuditingPersistenceEntity implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private ArrayList<Authority> authorities;

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }
}
