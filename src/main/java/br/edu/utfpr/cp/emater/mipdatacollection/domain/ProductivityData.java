/**
 * This file was generated by the Jeddict
 */
package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * @author gabriel
 */
@Embeddable
public class ProductivityData {

    @Basic
    private double productivityField;

    @Basic
    private double productivityFarmer;

    @Basic
    private boolean separatedWeight;

    public double getProductivityField() {
        return this.productivityField;
    }

    public void setProductivityField(double productivityField) {
        this.productivityField = productivityField;
    }

    public double getProductivityFarmer() {
        return this.productivityFarmer;
    }

    public void setProductivityFarmer(double productivityFarmer) {
        this.productivityFarmer = productivityFarmer;
    }

    public boolean isSeparatedWeight() {
        return this.separatedWeight;
    }

    public void setSeparatedWeight(boolean separatedWeight) {
        this.separatedWeight = separatedWeight;
    }

}