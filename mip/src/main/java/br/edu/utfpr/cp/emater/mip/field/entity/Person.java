package br.edu.utfpr.cp.emater.mip.field.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
}
