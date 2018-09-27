package br.edu.utfpr.cp.emater.mip.domain.pest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SamplePestSurvey implements Serializable {
    
    @Temporal (TemporalType.DATE)
    private Date sampleDate;
    
    private int daysAfterEmergence;
    private int defoliation;
    
    @Enumerated (EnumType.STRING)
    private GrowthPhase growthPhase;
    
    @ElementCollection
    private List<SamplePest> samplesPest; 
}
