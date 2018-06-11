package br.edu.utfpr.cp.emater.mipdatacollection.model;

import java.io.Serializable;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Named
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SamplePest implements Serializable {
    
    private Pest pest;
    private double value;
}
