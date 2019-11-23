package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
public class User implements Serializable {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter @Setter
    protected Long id;
    @Getter @Setter
    private String login;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private boolean enabled;
    @Getter @Setter
    @ManyToOne
    @JoinColumn
    private Role role;
    @Getter @Setter
    @OneToOne (optional = true)
    private Supervisor supervisor;
    @Getter @Setter
    @OneToOne (optional = true)
    private Farmer farmer;

    public User(Long id, String login, String password, boolean enabled, Role role, Supervisor supervisor, Farmer farmer) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.supervisor = supervisor;
        this.farmer = farmer;

        switch (this.getType()){
            case "Supervisor":
                this.name = supervisor.getName();
                break;

            case "Farmer":
                this.name = farmer.getName();
                break;

            default:
                this.name = "Administrator";
        }
    }

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
