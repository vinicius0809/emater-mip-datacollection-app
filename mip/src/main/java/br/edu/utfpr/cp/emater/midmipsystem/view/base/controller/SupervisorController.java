package br.edu.utfpr.cp.emater.midmipsystem.view.base.controller;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Region;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.RegionRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Supervisor;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.SupervisorRepository;
import br.edu.utfpr.cp.emater.midmipsystem.view.base.dto.SupervisorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping (value = "/supervisor")
public class SupervisorController {
    
    private final SupervisorRepository repository;
    private final RegionRepository regionRepository;

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
    public SupervisorController(SupervisorRepository repository, RegionRepository regionRepository, Environment environment) {
        this.repository = repository;
        this.regionRepository = regionRepository;
        this.environment = environment;
        this.operationSuccessMessage = false;

    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("supervisors", repository.findAll());
        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();
        
        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.supervisor"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.supervisor"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.supervisor"));
        
        return environment.getProperty("app.view.route.template.main.field.supervisor");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (SupervisorDTO supervisor) {
        
        Region r = regionRepository.findById(new Long(supervisor.getRegionId())).orElseThrow();

        Supervisor s = new Supervisor();
        s.setName(supervisor.getName());
        s.setEmail(supervisor.getEmail());
        s.setRegion(r);

        repository.save(s);

        this.setOperationSuccessMessage();
        
        return environment.getProperty("app.view.route.create.success.field.supervisor");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (SupervisorDTO supervisor) {
        
        Region r = regionRepository.findById(new Long(supervisor.getRegionId())).orElseThrow();

        Supervisor mr = repository.findById(new Long (supervisor.getId())).orElseThrow();
        mr.setName(supervisor.getName());
        mr.setEmail(supervisor.getEmail());
        mr.setRegion(r);
        
        repository.saveAndFlush(mr);

        this.setOperationSuccessMessage();
        
        return environment.getProperty("app.view.route.update.success.field.supervisor");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));

        this.setOperationSuccessMessage();
        
        return environment.getProperty("app.view.route.delete.success.field.supervisor");
    }
}
