package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import br.edu.utfpr.cp.emater.mipdatacollection.model.Farmer;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class FarmerBean implements Serializable {
    
    private String name;
    private Farmer currentFarmer;
    
    public List<Farmer> farmerList() {
        return Stream.of(
                new Farmer("Joao", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg"),
                new Farmer("Mario", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg"),
                new Farmer("Antonio", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg"),
                new Farmer("Roberto", "https://upload.wikimedia.org/wikipedia/commons/7/78/Joao_Lourenco_May_2017.jpg")
                ).collect(Collectors.toList());
    }
    
    public String onFarmerSelect(String farmerName) {
        this.setCurrentFarmer(
                this.farmerList().
                        stream().
                        filter(
                                farmer -> farmer.getName().
                                        equalsIgnoreCase(farmerName)
                        ).findFirst().
                        get()
        );
        
        return "/farmer/farmer-view";
    }
    
    public String create() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Novo produtor (" + this.getName() + ") criado com sucesso!", "Sucesso"));
        
        return "/farmer/index";
    }
    
    public String update() {
         FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Dados do produtor foram alterados!", "Sucesso"));
        
        return "/farmer/index";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Farmer getCurrentFarmer() {
        return currentFarmer;
    }

    public void setCurrentFarmer(Farmer currentFarmer) {
        this.currentFarmer = currentFarmer;
    }
}
