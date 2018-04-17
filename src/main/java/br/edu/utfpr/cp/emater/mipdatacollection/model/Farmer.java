package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import javax.inject.Named;

@Named
public class Farmer implements Serializable {
    
    private String name;
    private String pictureURL;

    public Farmer() {
    }

    public Farmer(String name, String pictureURL) {
        this.name = name;
        this.pictureURL = pictureURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
    
}