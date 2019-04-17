package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User extends Person implements Serializable {

    @Getter @Setter
    private String login;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private boolean enabled;
    @Getter @Setter
    @ManyToOne
    @JoinColumn
    private Role role;

    public User () {
        super();
    }

    public User (Long id, String name, String login,String password, boolean enabled, Role role) {
        this();

        this.setId (id);
        this.setName(name);
        this.setLogin(login);
        this.setPassword(password);
        this.setEnabled(enabled);
        this.setRole(role);
    }
}
