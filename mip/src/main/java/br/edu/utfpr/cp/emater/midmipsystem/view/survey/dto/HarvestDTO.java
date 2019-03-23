package br.edu.utfpr.cp.emater.midmipsystem.view.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HarvestDTO {

    private int id;
    private String name;
    private String begin;
    private String end;
}