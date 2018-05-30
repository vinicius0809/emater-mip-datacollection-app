package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
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

    @ManyToOne(targetEntity = Field.class)
    private Field field;

}