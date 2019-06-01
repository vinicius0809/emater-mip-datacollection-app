package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Role extends AuditingPersistenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String roleName;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String authCity;
    @Getter
    @Setter
    private String authRegion;
    @Getter
    @Setter
    private String authMacroregion;
    @Getter
    @Setter
    private String authField;
    @Getter
    @Setter
    private String authHarvest;
    @Getter
    @Setter
    private String authPest;
    @Getter
    @Setter
    private String authSupervisor;
    @Getter
    @Setter
    private String authUser;
    @Getter
    @Setter
    private String authFarmer;
    @Getter
    @Setter
    private String authSurveyField;
    @Getter
    @Setter
    private String authPestSurvey;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> user;

    public String getGrants(String domain) {

        switch (domain) {
            case "city":
                return authCity;
            case "region":
                return authRegion;
            case "macroregion":
                return authMacroregion;
            case "field":
                return authField;
            case "harvest":
                return authHarvest;
            case "pest":
                return authPest;
            case "supervisor":
                return authSupervisor;
            case "user":
                return authUser;
            case "farmer":
                return authFarmer;
            case "survey-field":
                return authSurveyField;
            case "pest-survey":
                return authPestSurvey;
            default:
                return "";
        }
    }
}
