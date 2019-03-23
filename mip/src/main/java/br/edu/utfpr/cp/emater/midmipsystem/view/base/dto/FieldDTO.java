package br.edu.utfpr.cp.emater.midmipsystem.view.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDTO {
    private int id;
    private String name;
    private String location;
    private int cityId;
    private int farmerId;
    private String supervisorIds[];
    
}