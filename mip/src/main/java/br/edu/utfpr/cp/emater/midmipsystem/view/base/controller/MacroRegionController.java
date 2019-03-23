package br.edu.utfpr.cp.emater.midmipsystem.view.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.MacroRegion;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.MacroRegionRepository;
import br.edu.utfpr.cp.emater.midmipsystem.view.base.dto.MacroRegionDTO;


@Controller
@RequestMapping (value = "/macroregion")
public class MacroRegionController {
    
    private final MacroRegionRepository repository;

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
    public MacroRegionController(MacroRegionRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
        this.operationSuccessMessage = false;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("macroregions", repository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();
        
        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.macroregion"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.macroregion"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.macroregion"));
        
        return environment.getProperty("app.view.route.template.main.field.macroregion");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    // public String create (@RequestParam String name) {
        public String create (MacroRegionDTO macroRegion) {        
        
        MacroRegion mr = new MacroRegion();
        mr.setName(macroRegion.getName());

        repository.save(mr);

        this.setOperationSuccessMessage();
        
        return environment.getProperty("app.view.route.create.success.field.macroregion");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (MacroRegionDTO macroRegion) {
        
        MacroRegion mr = repository.findById(new Long (macroRegion.getId())).orElseThrow();
        mr.setName(macroRegion.getName());
        
        repository.saveAndFlush(mr);

        this.setOperationSuccessMessage();
        
        return environment.getProperty("app.view.route.update.success.field.macroregion");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));

        this.setOperationSuccessMessage();
        
        return environment.getProperty("app.view.route.delete.success.field.macroregion");
    }
}
