package br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield;

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
@NoArgsConstructor
@AllArgsConstructor
public class DateData implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date sowedDate;

    @Temporal(TemporalType.DATE)
    private Date emergenceDate;

    @Temporal(TemporalType.DATE)
    private Date harvestDate;
}
