package br.edu.utfpr.cp.emater.mip.view.field;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.person.Supervisor;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.person.SupervisorRepository;
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

    private final Environment environment;

    @Autowired
    public SupervisorController(SupervisorRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("supervisors", repository.findAll());
        
        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.supervisor"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.supervisor"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.supervisor"));
        
        return environment.getProperty("app.view.route.template.main.field.supervisor");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (@RequestParam String name) {
        
        Supervisor s = new Supervisor();
        s.setName(name);

        repository.save(s);
        
        return environment.getProperty("app.view.route.create.success.field.supervisor");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        Supervisor mr = repository.findById(new Long (id)).get();
        mr.setName(name);
        
        repository.saveAndFlush(mr);
        
        return environment.getProperty("app.view.route.update.success.field.supervisor");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return environment.getProperty("app.view.route.delete.success.field.supervisor");
    }
}
