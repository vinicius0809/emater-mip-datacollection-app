package br.edu.utfpr.cp.emater.mip.survey.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionData implements Serializable {
    
    private boolean rustResistant;
    private boolean bt;
}
