package br.edu.utfpr.cp.emater.mip.survey.entity;

import br.edu.utfpr.cp.emater.mip.field.entity.Field;
import java.util.Date;
import org.junit.Test;

public class SurveyFieldTest {
    
    @Test
    public void create() {
        SurveyField sf = new SurveyField();
        
        sf.setDateData(new DateData(new Date(), new Date(), new Date()));
        sf.setField(new Field());
        sf.setLocationData(new LocationData(1.5, 3.5));
        sf.setName("Cultivar");
        sf.setProductivityData(new ProductivityData(1.5, 3.5, true));
        sf.setQuestionData(new QuestionData(true, true));
        sf.setSizeData(new SizeData(1.5, 1.5, 1.5));
        
    }
    
}
