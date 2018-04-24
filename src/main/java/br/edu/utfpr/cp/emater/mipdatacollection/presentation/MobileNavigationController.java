package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MobileNavigationController implements Serializable {

    private boolean useMobile = false;
    private int activetabindex = 0;

    public String menuNavigation(String view) {
        switch (view) {
            case "DASHBOARD":
                setActivetabindex(0);
                return "/index";
            case "SAMPLES":
                setActivetabindex(1);
                return "/sample-list";
            case "NEW":
                setActivetabindex(2);
                return "/new-sample";
            default:
                setActivetabindex(0);
                return "/index";
        }

    }

    public String initMobile() {
        setUseMobile(true);
        return null;
    }

    public boolean isUseMobile() {
        return useMobile;
    }

    public void setUseMobile(boolean useMobile) {
        this.useMobile = useMobile;
    }

    public int getActivetabindex() {
        return activetabindex;
    }

    public void setActivetabindex(int activetabindex) {
        this.activetabindex = activetabindex;
    }

}
