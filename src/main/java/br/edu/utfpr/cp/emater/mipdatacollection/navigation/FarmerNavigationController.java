package br.edu.utfpr.cp.emater.mipdatacollection.navigation;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FarmerNavigationController implements Serializable{

    public String menuNavigation(String view) {
        switch (view) {
            case "NEW":
                return "/farmer/farmer-create";
            default:
                return "/farmer/index";
        }

    }
}
