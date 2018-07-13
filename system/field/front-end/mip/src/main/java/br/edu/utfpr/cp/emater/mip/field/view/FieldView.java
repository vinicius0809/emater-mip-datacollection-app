package br.edu.utfpr.cp.emater.mip.field.view;

import br.edu.utfpr.cp.emater.mip.field.dto.FieldDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
@Getter
@Setter
public class FieldView implements Serializable {

    private List<FieldDTO> fields;

    private FieldDTO selectedField;

    private String name;
    private String location;
    private String city;
    private String farmer;
    private List<String> supervisors;

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

        if (fields == null) {
            fields = new ArrayList<>();
        }

        fields.add(new FieldDTO(name, location, city, farmer, supervisors));

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Sucesso!", String.format("Propriedade %s cadastrada!", name)));

        return "index";
    }

    public String delete() {
        fields.remove(selectedField);

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Sucesso!", String.format("Propriedade %s excluída!", selectedField.getName())));

        return "index";
    }

}
