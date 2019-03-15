package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.MacroRegion;
import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.text.WordUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region extends AuditingPersistenceEntity implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private MacroRegion macroRegion;

    @OneToMany
    private List<City> cities;

    public void setName (String name) {
        this.name = WordUtils.capitalize(name.toLowerCase());
    }

    public boolean addCity (City city) {

        if (!isThereACityContainer())
            createCityContainer();
        
        return this.getCities().add(city);
    }

    private boolean isThereACityContainer() {
        return this.getCities() == null;
    }

    private void createCityContainer() {
        this.setCities(new ArrayList<Cities>());
    }
}
