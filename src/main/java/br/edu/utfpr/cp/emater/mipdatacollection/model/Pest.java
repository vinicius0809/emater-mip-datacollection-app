package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.util.Objects;
import javax.inject.Named;

@Named
public class Pest {

    private String commonName;
    private String scientificName;
    private String figureURL;
    private PestType pestType;

    public Pest() {
    }

    public Pest(String commonName, String scientificName, String figureURL, PestType pestType) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.figureURL = figureURL;
        this.pestType = pestType;
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

    public PestType getPestType() {
        return pestType;
    }

    public void setPestType(PestType pestType) {
        this.pestType = pestType;
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
