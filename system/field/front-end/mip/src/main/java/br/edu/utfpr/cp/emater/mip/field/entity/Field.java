package br.edu.utfpr.cp.emater.mip.field.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    
    @NotNull
    private String name;
    
    @NotNull
    private String location;
    
    @NotNull @ManyToOne (fetch = FetchType.EAGER)
    private City city;
    
    @NotNull @ManyToOne
    private Farmer farmer;
    
    @NotNull @ManyToMany
    private Set<Supervisor> supervisors;
}
