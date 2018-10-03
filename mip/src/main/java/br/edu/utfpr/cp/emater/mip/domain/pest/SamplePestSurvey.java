package br.edu.utfpr.cp.emater.mip.domain.pest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SamplePestSurvey implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal (TemporalType.DATE)
    private Date sampleDate;
    
    private int daysAfterEmergence;
    private int defoliation;
    
    @Enumerated (EnumType.STRING)
    private GrowthPhase growthPhase;
    
    // @ElementCollection
    // private List<SamplePest> samplesPest; 
}
