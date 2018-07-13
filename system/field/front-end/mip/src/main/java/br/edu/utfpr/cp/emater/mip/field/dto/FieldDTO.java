package br.edu.utfpr.cp.emater.mip.field.dto;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Named
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO implements Serializable {
    
    private String name;
    private String location;
    private String city;
    private String Farmer;
    private List<String> supervisors;
    
}
