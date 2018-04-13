package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MobileNavigationController implements Serializable {

    private boolean useMobile = false;

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
}
