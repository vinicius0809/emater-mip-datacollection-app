package br.edu.utfpr.cp.emater.mip.view.survey;

import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.Harvest;
import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.HarvestRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum HarvestLabels {
    PAGE_TITLE("Gerenciamento de Safras"),
    ENTITY("Safra"),
    ARTICLE("a"),
    URL_CREATE("/harvest/create"),
    URL_UPDATE("/harvest/update"),
    URL_DELETE("/harvest/delete");

    private String value;

    HarvestLabels(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum HarvestPath {
    SUCCESS_CREATE("redirect:/harvest"),
    SUCCESS_UPDATE("redirect:/harvest"),
    SUCCESS_DELETE("redirect:/harvest"),
    SUCCESS_READ("redirect:/harvest"),
    TEMPLATE_PATH("/survey/harvest/index");

    private String value;

    HarvestPath(String value) {
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
@RequestMapping("/harvest")
public class HarvestController {

    private final HarvestRepository repository;

    @Autowired
    public HarvestController(HarvestRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("harvests", repository.findAll());

        data.addAttribute("pageTitle", HarvestLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", HarvestLabels.ARTICLE.getValue());
        data.addAttribute("entity", HarvestLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", HarvestLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", HarvestLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", HarvestLabels.URL_DELETE.getValue());

        return HarvestPath.TEMPLATE_PATH.getValue();
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
        
        return HarvestPath.SUCCESS_CREATE.getValue();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam String begin, @RequestParam String end) {

        Harvest hst = repository.findById(new Long(id)).get();
        hst.setName(name);
        hst.setBegin(this.dateFormatter(begin));
        hst.setEnd(this.dateFormatter(end));

        repository.saveAndFlush(hst);

        return HarvestPath.SUCCESS_UPDATE.getValue();
    }
    
    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById(new Long(id));
        
        return HarvestPath.SUCCESS_DELETE.getValue();
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
