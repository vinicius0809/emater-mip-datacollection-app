package br.edu.utfpr.cp.emater.mip.view.pest;

import br.edu.utfpr.cp.emater.midmipsystem.domain.mip.Pest;
import br.edu.utfpr.cp.emater.midmipsystem.domain.mip.PestRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.mip.PestSize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping (value = "/pest")
public class PestController {
    
    private final PestRepository repository;

    private final Environment environment;

    @Autowired
    public PestController(PestRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("pests", repository.findAll());
        data.addAttribute("pestSizes", PestSize.values());
        
        data.addAttribute("urlCreate", this.environment.getProperty("app.view.route.create.mip.pest"));
        data.addAttribute("urlUpdate", this.environment.getProperty("app.view.route.update.mip.pest"));
        data.addAttribute("urlDelete", this.environment.getProperty("app.view.route.delete.mip.pest"));
        
        return this.environment.getProperty("app.view.route.template.main.mip.pest");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (Pest pest) {
        
        repository.save(pest);
        
        return this.environment.getProperty("app.view.route.create.success.mip.pest");
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (Pest pest) {

        Pest originalPest = repository.findById(pest.getId()).get();
        originalPest.setUsualName(pest.getUsualName());
        originalPest.setScientificName(pest.getScientificName());
        originalPest.setPestSize(pest.getPestSize());
        
        repository.saveAndFlush(originalPest);
        
        return this.environment.getProperty("app.view.route.update.success.mip.pest");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return this.environment.getProperty("app.view.route.delete.success.mip.pest");
    }
}
