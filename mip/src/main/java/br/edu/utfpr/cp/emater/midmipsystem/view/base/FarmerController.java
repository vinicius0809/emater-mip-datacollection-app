package br.edu.utfpr.cp.emater.midmipsystem.view.base;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Farmer;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping (value = "/farmer")
public class FarmerController {
    
    private final FarmerRepository repository;

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
    public FarmerController(FarmerRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
        this.operationSuccessMessage = false;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("farmers", repository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();
        
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

        this.setOperationSuccessMessage();        
        
        return environment.getProperty("app.view.route.create.success.field.farmer");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        Farmer mr = repository.findById(new Long (id)).orElseThrow();
        mr.setName(name);
        
        repository.saveAndFlush(mr);

        this.setOperationSuccessMessage();        
        
        return environment.getProperty("app.view.route.update.success.field.farmer");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));

        this.setOperationSuccessMessage();        
        
        return environment.getProperty("app.view.route.delete.success.field.farmer");
    }
}
