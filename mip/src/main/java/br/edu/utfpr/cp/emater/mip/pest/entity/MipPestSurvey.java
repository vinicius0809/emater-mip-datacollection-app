package br.edu.utfpr.cp.emater.mip.pest.entity;

import br.edu.utfpr.cp.emater.mip.survey.entity.SurveyField;
import java.io.Serializable;
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
    private Set<SamplePest> samplePest;
}
