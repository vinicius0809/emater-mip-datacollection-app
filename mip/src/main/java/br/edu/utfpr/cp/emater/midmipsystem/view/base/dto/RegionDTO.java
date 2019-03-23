package br.edu.utfpr.cp.emater.midmipsystem.view.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {

    private int id;
    private String name;
    private int macroRegionId;
    private String citiesIDs[];
    
}