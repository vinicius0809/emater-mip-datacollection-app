package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import javax.inject.Named;

@Named
public class Farmer extends Person implements Serializable {
    
    public Farmer(String name, String pictureURL) {
        super (name, pictureURL);
    }
    
}
