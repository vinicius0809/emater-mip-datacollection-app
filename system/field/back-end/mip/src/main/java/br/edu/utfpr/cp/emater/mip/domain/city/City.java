package br.edu.utfpr.cp.emater.mip.domain.city;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.edu.utfpr.cp.emater.mip.domain.region.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    @Id @ GeneratedValue
    private Long id;
    private String name;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Region region;
}