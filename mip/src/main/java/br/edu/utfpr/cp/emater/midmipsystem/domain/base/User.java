package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter @Setter
    protected Long id;
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
    @Getter @Setter
    @OneToOne
    private Supervisor supervisor;
    @OneToOne (optional = true)
    private Farmer farmer;

    public String getType(){
        if(supervisor == null && farmer == null){
            return "Admin";
        }

        else if(farmer == null){
            return "Supervisor";
        }

        else{
            return "Farmer";
        }
    }
}
