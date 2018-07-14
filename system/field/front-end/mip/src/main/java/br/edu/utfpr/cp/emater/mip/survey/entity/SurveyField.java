package br.edu.utfpr.cp.emater.mip.survey.entity;

import br.edu.utfpr.cp.emater.mip.field.entity.Field;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyField implements Serializable {
    
    private String name;
    
    @Embedded
    private QuestionData questionData;
    
    @Embedded
    private DateData dateData;
    
    @Embedded
    private SizeData sizeData;
    
    @Embedded
    private LocationData locationData;
    
    @Embedded
    private ProductivityData productivityData;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private Field field;
}
