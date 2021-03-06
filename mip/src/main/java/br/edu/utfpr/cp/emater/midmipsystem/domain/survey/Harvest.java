package br.edu.utfpr.cp.emater.midmipsystem.domain.survey;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.text.WordUtils;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Harvest extends AuditingPersistenceEntity implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Temporal (TemporalType.DATE)
    private Date begin;
    
    @Temporal (TemporalType.DATE)
    private Date end;

    public void setName (String name) {
        this.name = WordUtils.capitalize(name.toLowerCase());
    }
}
