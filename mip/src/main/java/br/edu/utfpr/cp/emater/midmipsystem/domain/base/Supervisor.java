package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

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
