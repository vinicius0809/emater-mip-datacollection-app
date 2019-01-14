package br.edu.utfpr.cp.emater.midmipsystem.domain.mip;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.text.WordUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pest implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String usualName;
    private String scientificName;
    
    @Enumerated (EnumType.STRING)
    private PestSize pestSize;

    public void setUsualName (String usualName) {
        this.usualName = WordUtils.capitalize(usualName.toLowerCase());
    }

    public void setScientificName (String scientificName) {
        this.scientificName = WordUtils.capitalize(scientificName.toLowerCase());
    }
}
