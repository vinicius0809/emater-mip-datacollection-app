package br.edu.utfpr.cp.emater.midmipsystem.view.base;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.City;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.CityRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Field;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.FieldRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Farmer;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.FarmerRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Supervisor;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.SupervisorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/field")
public class FieldController {

    private final FieldRepository fieldRepository;
    private final CityRepository cityRepository;
    private final FarmerRepository farmerRepository;
    private final SupervisorRepository supervisorRepository;

    private final Environment environment;

    private boolean operationSuccessMessage;

    private void resetOperationSuccessMessage() {
        if (this.operationSuccessMessage)
            this.operationSuccessMessage = false;
    }

    private void setOperationSuccessMessage() {
        this.operationSuccessMessage = true;
    }

    @Autowired
    public FieldController(FieldRepository fieldRepository, CityRepository cityRepository, FarmerRepository farmerRepository, SupervisorRepository supervisorRepository, Environment environment) {
        this.fieldRepository = fieldRepository;
        this.cityRepository = cityRepository;
        this.farmerRepository = farmerRepository;
        this.supervisorRepository = supervisorRepository;
        this.environment = environment;
        this.operationSuccessMessage = false;

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("fields", fieldRepository.findAll());
        data.addAttribute("cities", cityRepository.findAll());
        data.addAttribute("farmers", farmerRepository.findAll());
        data.addAttribute("supervisors", supervisorRepository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();


        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.field"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.field"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.field"));

        return environment.getProperty("app.view.route.template.main.field.field");
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

        Field f = new Field();
        f.setCity(city);
        f.setFarmer(farmer);
        f.setLocation(location);
        f.setName(name);
        f.setSupervisors(supervisors);

        fieldRepository.save(f);

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.create.success.field.field");
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

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.update.success.field.field");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        fieldRepository.deleteById(new Long(id));

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.delete.success.field.field");
    }
}
