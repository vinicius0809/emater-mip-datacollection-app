package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import br.edu.utfpr.cp.emater.mipdatacollection.model.Pest;
import java.io.Serializable;
import java.util.Objects;
import javax.inject.Named;

@Named
public class PestSample implements Serializable {

    private Pest pest;
    private double value;

    public PestSample() {
    }

    public PestSample(Pest pest, double value) {
        this.pest = pest;
        this.value = value;
    }

    public Pest getPest() {
        return pest;
    }

    public void setPest(Pest pest) {
        this.pest = pest;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.pest);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
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
        final PestSample other = (PestSample) obj;
        if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        if (!Objects.equals(this.pest, other.pest)) {
            return false;
        }
        return true;
    }

}
