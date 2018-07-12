package br.edu.utfpr.cp.emater.mip.domain.macroRegion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacroRegion implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String name;
}