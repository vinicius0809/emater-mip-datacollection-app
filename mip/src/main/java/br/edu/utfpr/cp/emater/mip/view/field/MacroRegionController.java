package br.edu.utfpr.cp.emater.mip.view.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;


@Controller
@RequestMapping (value = "/macroregion")
public class MacroRegionController {
    
    private final MacroRegionRepository repository;

    private final Environment environment;

    @Autowired
    public MacroRegionController(MacroRegionRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("macroregions", repository.findAll());
        
        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.macroregion"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.macroregion"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.macroregion"));
        
        return environment.getProperty("app.view.route.template.main.field.macroregion");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (@RequestParam String name) {
        
        MacroRegion mr = new MacroRegion();
        mr.setName(name);

        repository.save(mr);
        
        return environment.getProperty("app.view.route.create.success.field.macroregion");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        MacroRegion mr = repository.findById(new Long (id)).get();
        mr.setName(name);
        
        repository.saveAndFlush(mr);
        
        return environment.getProperty("app.view.route.update.success.field.macroregion");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return environment.getProperty("app.view.route.delete.success.field.macroregion");
    }
}
