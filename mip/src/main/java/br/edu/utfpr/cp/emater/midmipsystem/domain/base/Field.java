package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.text.WordUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field extends AuditingPersistenceEntity implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String location;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private City city;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private Farmer farmer;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<Supervisor> supervisors;
    
    public boolean addSupervisor (Supervisor supervisor) {
        if (isThereSupervisorContainer())
            createSupervisorContainer();
        
        return this.getSupervisors().add(supervisor);
    }

    public void setName (String name) {
        this.name = WordUtils.capitalize(name.toLowerCase());
    }

    private boolean isThereSupervisorContainer() {
        return this.getSupervisors() == null;
    }

    private void createSupervisorContainer() {
        this.setSupervisors(new ArrayList<>());
    }
}
