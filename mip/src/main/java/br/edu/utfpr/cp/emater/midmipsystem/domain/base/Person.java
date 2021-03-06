package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.text.WordUtils;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person extends AuditingPersistenceEntity implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;

    public void setName (String name) {
        this.name = WordUtils.capitalize(name.toLowerCase());
    }
}
