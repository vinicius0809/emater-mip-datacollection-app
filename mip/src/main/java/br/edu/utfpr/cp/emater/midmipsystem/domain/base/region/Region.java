package br.edu.utfpr.cp.emater.midmipsystem.domain.base.region;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.macroregion.MacroRegion;
import java.io.Serializable;
import javax.persistence.Entity;
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
public class Region implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private MacroRegion macroRegion;

    public void setName (String name) {
        this.name = WordUtils.capitalize(name.toLowerCase());
    }
}
