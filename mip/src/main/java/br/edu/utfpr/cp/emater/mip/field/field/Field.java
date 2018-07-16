package br.edu.utfpr.cp.emater.mip.field.field;

import br.edu.utfpr.cp.emater.mip.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.field.area.City;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String location;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private City city;
    
    @ManyToOne
    private Farmer farmer;
    
    @ManyToMany
    private Set<Supervisor> supervisors;
}
