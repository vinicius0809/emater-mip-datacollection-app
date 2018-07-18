package br.edu.utfpr.cp.emater.mip.pest;

import br.edu.utfpr.cp.emater.mip.survey.surveyfield.SurveyField;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MipPestSurvey implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne (fetch = FetchType.EAGER)
    private SurveyField surveyField;
        
    @ElementCollection (fetch = FetchType.EAGER)
    private Set<SamplePest> samplePestSet;
    
    public boolean addSamplePest (Date sampleDate, int daysAfterEmergence, int defoliation, GrowthPhase growthPhase, Pest pest, double value) {
        if (this.getSamplePestSet()== null)
            this.setSamplePestSet(new HashSet<>());
        
        return this.getSamplePestSet().add(new SamplePest(sampleDate, daysAfterEmergence, defoliation, growthPhase, value, pest));
    }
}
