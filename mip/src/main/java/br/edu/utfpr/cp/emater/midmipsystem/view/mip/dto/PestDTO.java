package br.edu.utfpr.cp.emater.midmipsystem.view.mip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PestDTO {
    
    private int id;
    private String usualName;
    private String scientificName;
    private String pestSize;
}