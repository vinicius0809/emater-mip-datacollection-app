package br.edu.utfpr.cp.emater.mip.view;

import br.edu.utfpr.cp.emater.mip.domain.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.domain.field.person.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum FarmerLabels {
    PAGE_TITLE ("Gerenciamento de Produtores"),
    ENTITY ("Produtor"),
    ARTICLE ("o"),
    URL_CREATE ("/farmer/create"),
    URL_UPDATE ("/farmer/update"),
    URL_DELETE ("/farmer/delete");
    
    private String value;
    
    FarmerLabels (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum FarmerPath {
    SUCCESS_CREATE ("redirect:/farmer"),
    SUCCESS_UPDATE ("redirect:/farmer"),
    SUCCESS_DELETE ("redirect:/farmer"),
    SUCCESS_READ ("redirect:/farmer"),
    TEMPLATE_PATH ("/field/farmer/index");
    
    private String value;
    
    FarmerPath (String value) {
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
@RequestMapping (value = "/farmer")
public class FarmerController {
    
    private final FarmerRepository repository;

    @Autowired
    public FarmerController(FarmerRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("farmers", repository.findAll());
        
        data.addAttribute("pageTitle", FarmerLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", FarmerLabels.ARTICLE.getValue());
        data.addAttribute("entity", FarmerLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", FarmerLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", FarmerLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", FarmerLabels.URL_DELETE.getValue());
        
        return FarmerPath.TEMPLATE_PATH.getValue();
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (@RequestParam String name) {
        
        repository.save(new Farmer(null, name));
        return FarmerPath.SUCCESS_CREATE.getValue();
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        Farmer mr = repository.findById(new Long (id)).get();
        mr.setName(name);
        
        repository.saveAndFlush(mr);
        
        return FarmerPath.SUCCESS_UPDATE.getValue();
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return FarmerPath.SUCCESS_DELETE.getValue();
    }
}
