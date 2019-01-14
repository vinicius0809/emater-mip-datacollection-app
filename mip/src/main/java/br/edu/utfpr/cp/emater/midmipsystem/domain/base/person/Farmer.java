package br.edu.utfpr.cp.emater.midmipsystem.domain.base.person;

import java.io.Serializable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Farmer extends Person implements Serializable {

    public Farmer () {
        super();
    }

    public Farmer (Long id, String name) {
        this();

        this.setId (id);
        this.setName (name);
    }
    
}
