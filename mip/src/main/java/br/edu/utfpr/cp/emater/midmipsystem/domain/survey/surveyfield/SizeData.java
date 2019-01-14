package br.edu.utfpr.cp.emater.midmipsystem.domain.survey.surveyfield;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeData implements Serializable {
    
    private double totalArea;
    private double totalPlantedArea;
    private double plantPerMeter;
}
