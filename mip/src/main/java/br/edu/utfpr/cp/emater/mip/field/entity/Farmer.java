package br.edu.utfpr.cp.emater.mip.field.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Farmer extends Person implements Serializable {
    
    public Farmer (Long id, String name) {
        this();
        
        this.setId(id);
        this.setName(name);
    }
}
