package br.edu.utfpr.cp.emater.mip.field.view;

import br.edu.utfpr.cp.emater.mip.field.entity.Field;
import br.edu.utfpr.cp.emater.mip.field.service.FieldService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class FieldView implements Serializable {

    private Field selectedField;
    
    private String name;
    private String location;
    private String city;
    private String farmer;
    private List<String> supervisors;
    
    @EJB
    private FieldService fieldService;
    
    public List<Field> readAllFields() {
        System.out.println(fieldService.readAll());
        return new ArrayList<Field>(fieldService.readAll());
    }

    public List<String> readAllSupervisors() {
        return Stream.of("John Lucky", "Anne Lucky", "Andrew Lucky").collect(Collectors.toList());
    }

    public List<String> readAllCities() {
        return Stream.of("Londrina", "Maringá", "Paranavaí").collect(Collectors.toList());
    }

    public List<String> readAllFarmers() {
        return Stream.of("John Doe", "Anne Doe", "Andrew Doe").collect(Collectors.toList());
    }

    public String create() {

        

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Sucesso!", String.format("Propriedade %s cadastrada!", name)));

        return "index";
    }

    public String delete() {
        
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Sucesso!", String.format("Propriedade %s excluída!", selectedField.getName())));

        return "index";
    }

}
