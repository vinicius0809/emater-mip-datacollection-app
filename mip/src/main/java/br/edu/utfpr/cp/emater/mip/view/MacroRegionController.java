package br.edu.utfpr.cp.emater.mip.view;

import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum MacroRegionLabels {
    PAGE_TITLE ("Gerenciamento de Macrorregiões"),
    ENTITY ("Macrorregião"),
    ARTICLE ("a"),
    URL_CREATE ("/macroregion/create"),
    URL_UPDATE ("/macroregion/update"),
    URL_DELETE ("/macroregion/delete");
    
    private String value;
    
    MacroRegionLabels (String value) {
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
@RequestMapping (value = "/macroregion")
public class MacroRegionController {
    
    private final MacroRegionRepository repository;

    @Autowired
    public MacroRegionController(MacroRegionRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("macroregions", repository.findAll());
        
        data.addAttribute("pageTitle", MacroRegionLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", MacroRegionLabels.ARTICLE.getValue());
        data.addAttribute("entity", MacroRegionLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", MacroRegionLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", MacroRegionLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", MacroRegionLabels.URL_DELETE.getValue());
        
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
