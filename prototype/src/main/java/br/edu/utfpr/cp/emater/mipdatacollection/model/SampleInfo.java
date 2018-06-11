package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.inject.Named;

@Named
public class SampleInfo implements Serializable {
    
    private int DAE;
    private LocalDate collectionDate;
    private String cultureStage;
    private Crop crop;
    private int defoliation;

    public SampleInfo() {
    }

    public SampleInfo(int DAE, LocalDate collectionDate, String cultureStage, int defoliation) {
        this();
        
        this.setDAE(DAE);
        this.setCollectionDate(collectionDate);
        this.setCultureStage(cultureStage);
        this.setDefoliation(defoliation);
    }

    public int getDAE() {
        return DAE;
    }

    public void setDAE(int DAE) {
        this.DAE = DAE;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getCultureStage() {
        return cultureStage;
    }

    public void setCultureStage(String cultureStage) {
        this.cultureStage = cultureStage;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public int getDefoliation() {
        return defoliation;
    }

    public void setDefoliation(int defoliation) {
        this.defoliation = defoliation;
    }

}
