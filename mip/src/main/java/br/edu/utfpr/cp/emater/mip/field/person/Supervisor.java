package br.edu.utfpr.cp.emater.mip.field.person;

import java.io.Serializable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Supervisor extends Person implements Serializable {
    
    public Supervisor () {
        super();
    }

    public Supervisor (Long id, String name) {
        this();

        this.setId (id);
        this.setName (name);
    }
    
}
