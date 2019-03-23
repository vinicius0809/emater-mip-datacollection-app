package br.edu.utfpr.cp.emater.midmipsystem.view.survey.controller;

import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.Harvest;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.HarvestRepository;
import br.edu.utfpr.cp.emater.midmipsystem.view.survey.dto.HarvestDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/harvest")
public class HarvestController {

    private final HarvestRepository repository;

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
    public HarvestController(HarvestRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
        this.operationSuccessMessage = false;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("harvests", repository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();

        data.addAttribute("urlCreate", this.environment.getProperty("app.view.route.create.survey.harvest"));
        data.addAttribute("urlUpdate", this.environment.getProperty("app.view.route.update.survey.harvest"));
        data.addAttribute("urlDelete", this.environment.getProperty("app.view.route.delete.survey.harvest"));

        return this.environment.getProperty("app.view.route.template.main.survey.harvest");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HarvestDTO harvest) {

        Date formattedBegin = this.dateFormatter(harvest.getBegin());
        Date formattedEnd = this.dateFormatter(harvest.getEnd());

        Harvest h = new Harvest();
        h.setBegin(formattedBegin);
        h.setEnd(formattedEnd);
        h.setName(harvest.getName());

        repository.save(h);

        this.setOperationSuccessMessage();
        
        return this.environment.getProperty("app.view.route.create.success.survey.harvest");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HarvestDTO harvest) {

        Harvest hst = repository.findById(new Long(harvest.getId())).orElseThrow();
        hst.setName(harvest.getName());
        hst.setBegin(this.dateFormatter(harvest.getBegin()));
        hst.setEnd(this.dateFormatter(harvest.getEnd()));

        repository.saveAndFlush(hst);

        this.setOperationSuccessMessage();

        return this.environment.getProperty("app.view.route.update.success.survey.harvest");
    }
    
    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));

        this.setOperationSuccessMessage();
        
        return this.environment.getProperty("app.view.route.delete.success.survey.harvest");
    }

    private Date dateFormatter(String date) {
        Date theDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        try {
            theDate = formatter.parse(date);
            return theDate;

        } catch (ParseException ex) {
            Logger.getLogger(HarvestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return theDate;
    }
}
