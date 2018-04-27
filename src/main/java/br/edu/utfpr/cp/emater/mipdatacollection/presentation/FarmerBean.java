package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import br.edu.utfpr.cp.emater.mipdatacollection.model.Farmer;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class FarmerBean implements Serializable {
    
    public List<Farmer> farmerList() {
        return Stream.of(
                new Farmer("Joao", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg"),
                new Farmer("Mario", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg"),
                new Farmer("Antonio", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg"),
                new Farmer("Roberto", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg")
                ).collect(Collectors.toList());
    }
    
    public String onFarmerSelect() {
        return "/farmer/farmer-list";
    }
    
}
