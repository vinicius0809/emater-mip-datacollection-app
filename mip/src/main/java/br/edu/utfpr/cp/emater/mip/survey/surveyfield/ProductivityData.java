package br.edu.utfpr.cp.emater.mip.survey.surveyfield;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductivityData implements Serializable {
    
    private double productivityField;
    private double productivityFarmer;
    private boolean separatedWeight;
}
