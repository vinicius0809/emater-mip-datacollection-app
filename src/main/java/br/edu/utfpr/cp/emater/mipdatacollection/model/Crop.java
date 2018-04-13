package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import javax.inject.Named;

@Named
public class Crop implements Serializable {
    
    private String name;
    private Farmer farmer;
    private Harvest harvest;

    public Crop() {
    }

    public Crop(String name, Farmer farmer, Harvest harvest) {
        this.name = name;
        this.farmer = farmer;
        this.harvest = harvest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }
    
}
