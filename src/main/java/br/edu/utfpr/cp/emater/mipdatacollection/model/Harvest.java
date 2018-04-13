package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import javax.inject.Named;

@Named
public class Harvest implements Serializable {

    private String name;
    
    public Harvest() {
    }

    public Harvest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
