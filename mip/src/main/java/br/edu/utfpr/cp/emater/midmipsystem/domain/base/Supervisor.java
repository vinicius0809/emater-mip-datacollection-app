package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Supervisor extends Person implements Serializable {

    private String email;
    
    public Supervisor () {
        super();
    }

    public Supervisor (Long id, String name, String email) {
        this();

        this.setId (id);
        this.setName (name);
        this.setEmail (email);
    }
    
}
