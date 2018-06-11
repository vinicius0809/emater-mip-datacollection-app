package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class DateData implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date sowedDate;

    @Temporal(TemporalType.DATE)
    private Date emergenceDate;

    @Temporal(TemporalType.DATE)
    private Date harvestDate;

}