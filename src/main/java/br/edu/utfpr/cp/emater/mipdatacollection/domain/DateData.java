/**
 * This file was generated by the Jeddict
 */
package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author gabriel
 */
@Embeddable
public class DateData {

    @Basic
    @Temporal(TemporalType.DATE)
    private Date sowedDate;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date emergenceDate;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date harvestDate;

    public Date getSowedDate() {
        return this.sowedDate;
    }

    public void setSowedDate(Date sowedDate) {
        this.sowedDate = sowedDate;
    }

    public Date getEmergenceDate() {
        return this.emergenceDate;
    }

    public void setEmergenceDate(Date emergenceDate) {
        this.emergenceDate = emergenceDate;
    }

    public Date getHarvestDate() {
        return this.harvestDate;
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate = harvestDate;
    }

}