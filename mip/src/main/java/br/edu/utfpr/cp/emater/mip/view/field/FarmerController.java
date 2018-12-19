package br.edu.utfpr.cp.emater.mip.view.field;

import br.edu.utfpr.cp.emater.mip.domain.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.domain.field.person.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum FarmerLabels {
    PAGE_TITLE ("Gerenciamento de Produtores"),
    ENTITY ("Produtor"),
    ARTICLE ("o");
    
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

@Controller
@RequestMapping (value = "/farmer")
public class FarmerController {
    
    private final FarmerRepository repository;

    private final Environment environment;

    @Autowired
    public FarmerController(FarmerRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("farmers", repository.findAll());
        
        data.addAttribute("pageTitle", FarmerLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", FarmerLabels.ARTICLE.getValue());
        data.addAttribute("entity", FarmerLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.farmer"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.farmer"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.farmer"));
        
        return environment.getProperty("app.view.route.template.main.field.farmer");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (@RequestParam String name) {
        
        Farmer f = new Farmer();
        f.setName(name);

        repository.save(f);
        
        return environment.getProperty("app.view.route.create.success.field.farmer");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        Farmer mr = repository.findById(new Long (id)).get();
        mr.setName(name);
        
        repository.saveAndFlush(mr);
        
        return environment.getProperty("app.view.route.update.success.field.farmer");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return environment.getProperty("app.view.route.delete.success.field.farmer");
    }
}
