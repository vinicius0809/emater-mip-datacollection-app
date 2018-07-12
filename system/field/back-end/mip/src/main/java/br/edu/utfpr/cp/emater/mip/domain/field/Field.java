package br.edu.utfpr.cp.emater.mip.domain.field;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.edu.utfpr.cp.emater.mip.domain.city.City;
import br.edu.utfpr.cp.emater.mip.domain.farmer.Farmer;
import br.edu.utfpr.cp.emater.mip.domain.supervisor.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field implements Serializable {

    @Id @GeneratedValue
    private Long id;

    private String description;
    private String location;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private City city;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Farmer farmer;

    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Supervisor> supervisors;
}