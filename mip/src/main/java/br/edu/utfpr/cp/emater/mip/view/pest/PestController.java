package br.edu.utfpr.cp.emater.mip.view.pest;

import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.Pest;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestSize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum PestLabels {
    PAGE_TITLE ("Gerenciamento de Insetos Praga"),
    ENTITY ("Praga"),
    ARTICLE ("a"),
    URL_CREATE ("/pest/create"),
    URL_UPDATE ("/pest/update"),
    URL_DELETE ("/pest/delete");
    
    private String value;
    
    PestLabels (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum PestPath {
    SUCCESS_CREATE ("redirect:/pest"),
    SUCCESS_UPDATE ("redirect:/pest"),
    SUCCESS_DELETE ("redirect:/pest"),
    SUCCESS_READ ("redirect:/pest"),
    TEMPLATE_PATH ("/pest/pest/index");
    
    private String value;
    
    PestPath (String value) {
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
@RequestMapping (value = "/pest")
public class PestController {
    
    private final PestRepository repository;

    @Autowired
    public PestController(PestRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("pests", repository.findAll());
        data.addAttribute("pestSizes", PestSize.values());
        
        data.addAttribute("pageTitle", PestLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", PestLabels.ARTICLE.getValue());
        data.addAttribute("entity", PestLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", PestLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", PestLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", PestLabels.URL_DELETE.getValue());
        
        return PestPath.TEMPLATE_PATH.getValue();
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (Pest pest) {
        
        repository.save(pest);
        
        return PestPath.SUCCESS_CREATE.getValue();
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (Pest pest) {
        System.out.println(pest);
        
        // MacroRegion mr = repository.findById(new Long (id)).get();
        // mr.setName(name);
        
        // repository.saveAndFlush(mr);
        
        return PestPath.SUCCESS_UPDATE.getValue();
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return PestPath.SUCCESS_DELETE.getValue();
    }
}
