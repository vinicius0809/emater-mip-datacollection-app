package br.edu.utfpr.cp.emater.midmipsystem.view.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyFieldDTO {

    private int id;
    private int fieldId;
    private int harvestId;
    private String seedName;
    private String sowedDate;
    private String emergenceDate;
    private String harvestDate;
    private boolean rustResistant;
    private boolean bt;
    private boolean sporeCollector;
    private double totalArea;
    private double totalPlantedArea;
    private double plantPerMeter;
    private double productivityField;
    private double productivityFarmer;
    private boolean separatedWeight;
    private double latitute;
    private double longitude;
}