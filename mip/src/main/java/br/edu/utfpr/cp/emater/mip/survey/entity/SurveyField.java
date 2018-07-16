package br.edu.utfpr.cp.emater.mip.survey.entity;

import br.edu.utfpr.cp.emater.mip.field.field.Field;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyField implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    
    @ManyToOne (fetch = FetchType.EAGER)
    private Harvest harvest;
}
