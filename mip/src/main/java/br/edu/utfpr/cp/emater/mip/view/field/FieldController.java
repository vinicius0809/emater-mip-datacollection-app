package br.edu.utfpr.cp.emater.mip.view.field;

import br.edu.utfpr.cp.emater.mip.domain.field.city.City;
import br.edu.utfpr.cp.emater.mip.domain.field.city.CityRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.field.Field;
import br.edu.utfpr.cp.emater.mip.domain.field.field.FieldRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.domain.field.person.FarmerRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.domain.field.person.SupervisorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum FieldLabels {
    PAGE_TITLE("Gerenciamento de Unidades de Referência"),
    ENTITY("Unidade de Referência"),
    ARTICLE("a"),
    URL_CREATE("/field/create"),
    URL_UPDATE("/field/update"),
    URL_DELETE("/field/delete");

    private String value;

    FieldLabels(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum FieldPath {
    SUCCESS_CREATE("redirect:/field"),
    SUCCESS_UPDATE("redirect:/field"),
    SUCCESS_DELETE("redirect:/field"),
    SUCCESS_READ("redirect:/field"),
    TEMPLATE_PATH("/field/field/index");

    private String value;

    FieldPath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

@Controller
@RequestMapping(value = "/field")
public class FieldController {

    private final FieldRepository fieldRepository;
    private final CityRepository cityRepository;
    private final FarmerRepository farmerRepository;
    private final SupervisorRepository supervisorRepository;

    @Autowired
    public FieldController(FieldRepository fieldRepository, CityRepository cityRepository, FarmerRepository farmerRepository, SupervisorRepository supervisorRepository) {
        this.fieldRepository = fieldRepository;
        this.cityRepository = cityRepository;
        this.farmerRepository = farmerRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("fields", fieldRepository.findAll());
        data.addAttribute("cities", cityRepository.findAll());
        data.addAttribute("farmers", farmerRepository.findAll());
        data.addAttribute("supervisors", supervisorRepository.findAll());

        data.addAttribute("pageTitle", FieldLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", FieldLabels.ARTICLE.getValue());
        data.addAttribute("entity", FieldLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", FieldLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", FieldLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", FieldLabels.URL_DELETE.getValue());

        return FieldPath.TEMPLATE_PATH.getValue();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam int cityId,
            @RequestParam int farmerId,
            @RequestParam String supervisorIds[]) {

        City city = cityRepository.findById(new Long(cityId)).get();
        Farmer farmer = farmerRepository.findById(new Long(farmerId)).get();

        List<Supervisor> supervisors = new ArrayList<>();
        for (String supervisorId : supervisorIds) {
            supervisors.add(supervisorRepository.findById(new Long(supervisorId)).get());
        }

        fieldRepository.save(new Field(null, name, location, city, farmer, supervisors));

        return FieldPath.SUCCESS_CREATE.getValue();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam int cityId,
            @RequestParam int farmerId,
            @RequestParam String supervisorIds[]) {

        City city = cityRepository.findById(new Long(cityId)).get();
        Farmer farmer = farmerRepository.findById(new Long(farmerId)).get();

        List<Supervisor> supervisors = new ArrayList<>();
        for (String supervisorId : supervisorIds) {
            supervisors.add(supervisorRepository.findById(new Long(supervisorId)).get());
        }
        
        Field field = fieldRepository.findById(new Long (id)).get();
        field.setName(name);
        field.setLocation(location);
        field.setCity(city);
        field.setFarmer(farmer);
        field.setSupervisors(supervisors);

        fieldRepository.saveAndFlush(field);

        return FieldPath.SUCCESS_UPDATE.getValue();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        fieldRepository.deleteById(new Long(id));

        return FieldPath.SUCCESS_DELETE.getValue();
    }
}
