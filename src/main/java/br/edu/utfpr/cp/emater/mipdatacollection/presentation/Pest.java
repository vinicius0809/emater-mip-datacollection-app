package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import java.util.Objects;
import javax.inject.Named;

@Named
public class Pest {

    private String commonName;
    private String scientificName;
    private String figureURL;

    public Pest() {
    }

    public Pest(String commonName, String scientificName, String figureURL) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.figureURL = figureURL;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFigureURL() {
        return figureURL;
    }

    public void setFigureURL(String figureURL) {
        this.figureURL = figureURL;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.scientificName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pest other = (Pest) obj;
        if (!Objects.equals(this.scientificName, other.scientificName)) {
            return false;
        }
        return true;
    }

}
