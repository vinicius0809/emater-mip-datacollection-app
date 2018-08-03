package br.edu.utfpr.cp.emater.mip.view;

import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping (value = "/macroregion")
public class MacroRegionController {
    
    private MacroRegionRepository repository;

    @Autowired
    public MacroRegionController(MacroRegionRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("macroregions", repository.findAll());
        data.addAttribute("title", "Gerenciamento de Macrorregiões");
        return "/field/macroregion/index";
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.GET)
    public String create (@RequestParam String name) {
        
        repository.save(new MacroRegion(null, name));
        return "/macroregion";
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.GET)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        MacroRegion mr = repository.findById(new Long (id)).get();
        mr.setName(name);
        
        repository.saveAndFlush(mr);
        
        return "/macroregion";
    }

    @RequestMapping (value = "/delete", method = RequestMethod.GET)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return "/macroregion";
    }
}
