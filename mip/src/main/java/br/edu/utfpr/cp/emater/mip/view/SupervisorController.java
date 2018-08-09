package br.edu.utfpr.cp.emater.mip.view;

import br.edu.utfpr.cp.emater.mip.domain.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.domain.field.person.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum SupervisorLabels {
    PAGE_TITLE ("Gerenciamento de Responsáveis Técnicos"),
    ENTITY ("Responsável Técnico"),
    ARTICLE ("o"),
    URL_CREATE ("/supervisor/create"),
    URL_UPDATE ("/supervisor/update"),
    URL_DELETE ("/supervisor/delete");
    
    private String value;
    
    SupervisorLabels (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum SupervisorPath {
    SUCCESS_CREATE ("redirect:/supervisor"),
    SUCCESS_UPDATE ("redirect:/supervisor"),
    SUCCESS_DELETE ("redirect:/supervisor"),
    SUCCESS_READ ("redirect:/supervisor"),
    TEMPLATE_PATH ("/field/supervisor/index");
    
    private String value;
    
    SupervisorPath (String value) {
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
@RequestMapping (value = "/supervisor")
public class SupervisorController {
    
    private final SupervisorRepository repository;

    @Autowired
    public SupervisorController(SupervisorRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("supervisors", repository.findAll());
        
        data.addAttribute("pageTitle", SupervisorLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", SupervisorLabels.ARTICLE.getValue());
        data.addAttribute("entity", SupervisorLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", SupervisorLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", SupervisorLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", SupervisorLabels.URL_DELETE.getValue());
        
        return SupervisorPath.TEMPLATE_PATH.getValue();
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (@RequestParam String name) {
        
        repository.save(new Supervisor(null, name));
        return SupervisorPath.SUCCESS_CREATE.getValue();
    }
    
    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (@RequestParam String name, @RequestParam int id) {
        
        Supervisor mr = repository.findById(new Long (id)).get();
        mr.setName(name);
        
        repository.saveAndFlush(mr);
        
        return SupervisorPath.SUCCESS_UPDATE.getValue();
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return SupervisorPath.SUCCESS_DELETE.getValue();
    }
}
