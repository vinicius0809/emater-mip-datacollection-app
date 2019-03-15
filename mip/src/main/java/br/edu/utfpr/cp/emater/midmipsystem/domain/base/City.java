package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Region;
import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.text.WordUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City extends AuditingPersistenceEntity implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Enumerated (EnumType.STRING)
    private State state;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private Region region;  
    
    public City (Long id, String name, State state) {
        this.setId(id);
        this.setName(name);
        this.setState(state);
    }

    public void setName (String name) {
        this.name = WordUtils.capitalize(name.toLowerCase());
    }
}
