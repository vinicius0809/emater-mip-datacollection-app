package br.edu.utfpr.cp.emater.mip.domain.supervisor;

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
public class Supervisor implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String name;
}