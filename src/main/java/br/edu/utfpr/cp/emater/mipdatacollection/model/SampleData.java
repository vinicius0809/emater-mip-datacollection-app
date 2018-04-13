package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import javax.inject.Named;

@Named
public class SampleData implements Serializable {
    
    private Pest pest;
    private SampleInfo info;
    private double data;

    public SampleData() {
    }

    public SampleData(Pest pest, SampleInfo info, double data) {
        this.pest = pest;
        this.info = info;
        this.data = data;
    }

    public Pest getPest() {
        return pest;
    }

    public void setPest(Pest pest) {
        this.pest = pest;
    }

    public SampleInfo getInfo() {
        return info;
    }

    public void setInfo(SampleInfo info) {
        this.info = info;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
    
}
