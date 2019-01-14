package br.edu.utfpr.cp.emater.mip.view.survey;

import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.harvest.Harvest;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.harvest.HarvestRepository;
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

    @Autowired
    public HarvestController(HarvestRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("harvests", repository.findAll());

        data.addAttribute("urlCreate", this.environment.getProperty("app.view.route.create.survey.harvest"));
        data.addAttribute("urlUpdate", this.environment.getProperty("app.view.route.update.survey.harvest"));
        data.addAttribute("urlDelete", this.environment.getProperty("app.view.route.delete.survey.harvest"));

        return this.environment.getProperty("app.view.route.template.main.survey.harvest");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam String begin, @RequestParam String end) {

        Date formattedBegin = this.dateFormatter(begin);
        Date formattedEnd = this.dateFormatter(end);

        Harvest h = new Harvest();
        h.setBegin(formattedBegin);
        h.setEnd(formattedEnd);
        h.setName(name);

        repository.save(h);
        
        return this.environment.getProperty("app.view.route.create.success.survey.harvest");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam String begin, @RequestParam String end) {

        Harvest hst = repository.findById(new Long(id)).get();
        hst.setName(name);
        hst.setBegin(this.dateFormatter(begin));
        hst.setEnd(this.dateFormatter(end));

        repository.saveAndFlush(hst);

        return this.environment.getProperty("app.view.route.update.success.survey.harvest");
    }
    
    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
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
