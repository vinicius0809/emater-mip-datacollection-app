package br.edu.utfpr.cp.emater.mip.view.survey;

import br.edu.utfpr.cp.emater.mip.domain.field.field.Field;
import br.edu.utfpr.cp.emater.mip.domain.field.field.FieldRepository;
import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.HarvestRepository;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.DateData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.LocationData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.ProductivityData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.QuestionData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SizeData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SurveyField;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SurveyFieldRepository;
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

enum SurveyFieldLabels {
    PAGE_TITLE("Unidades de Referência Participantes da Pesquisa"),
    SELECT_FIELD_PAGE_TITLE("Selecionar Unidades de Referência para Pesquisa"),
//    FIELD_FORM_PAGE_TITLE("Dados da Unidade de Referência para Pesquisa"),
    ENTITY("Unidade de Referência"),
    ARTICLE("a");

    private String value;

    SurveyFieldLabels(String value) {
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
@RequestMapping ("/survey-field")
public class SurveyFieldController {
    
    private final SurveyFieldRepository surveyFieldRepository;
    private final FieldRepository fieldRepository;
    private final HarvestRepository harvestRepository;

    private final Environment environment;

    @Autowired
    public SurveyFieldController(SurveyFieldRepository surveyFieldRepository, FieldRepository fieldRepository, HarvestRepository harvestRepository, Environment environment) {
        this.surveyFieldRepository = surveyFieldRepository;
        this.fieldRepository = fieldRepository;
        this.harvestRepository = harvestRepository;
        this.environment = environment;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String findAll (Model data) {
        data.addAttribute("surveyFields", surveyFieldRepository.findAll());

        data.addAttribute("pageTitle", SurveyFieldLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", SurveyFieldLabels.ARTICLE.getValue());
        data.addAttribute("entity", SurveyFieldLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", this.environment.getProperty("app.view.route.create.survey.survey-field"));
        data.addAttribute("urlUpdate", this.environment.getProperty("app.view.route.update.survey.survey-field"));
        data.addAttribute("urlDelete", this.environment.getProperty("app.view.route.delete.survey.survey-field"));

        return this.environment.getProperty("app.view.route.template.main.survey.survey-field");
    }
    
    @RequestMapping (value = "/select-field", method = RequestMethod.GET)
    public String selectFieldForSurvey (Model data) {
        data.addAttribute("fields", fieldRepository.findAll());
        data.addAttribute("pageTitle", SurveyFieldLabels.SELECT_FIELD_PAGE_TITLE.getValue());
                
        return this.environment.getProperty("app.view.route.template.select-field.survey.survey-field");
    }
    
    @RequestMapping (value = "/field-form", method = RequestMethod.GET)
    public String surveyFieldForm (@RequestParam int fieldId, Model data) {
        data.addAttribute("harvests", harvestRepository.findAll());
        
        Field selectedField = fieldRepository.findById(new Long(fieldId)).get();
        
        data.addAttribute("selectedField", selectedField);
                
        data.addAttribute("pageTitle", String.format("Dados da UR '%s' (%s, %s)", selectedField.getName(), selectedField.getFarmer().getName(), selectedField.getCity().getName()));
        
        return this.environment.getProperty("app.view.route.template.form.survey.survey-field");
    }
    
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (
                            @RequestParam int fieldId, 
                            @RequestParam int harvestId,
                            @RequestParam String name,
                            @RequestParam String sowedDate,
                            @RequestParam String emergenceDate,
                            @RequestParam String harvestDate,
                            @RequestParam (required = false) boolean rustResistant,
                            @RequestParam (required = false) boolean bt,
                            @RequestParam double totalArea,
                            @RequestParam double totalPlantedArea,
                            @RequestParam double plantPerMeter,
                            @RequestParam double productivityField,
                            @RequestParam double productivityFarmer,
                            @RequestParam (required = false) boolean separatedWeight,
                            @RequestParam double latitute,
                            @RequestParam double longitude) {
        
        SurveyField sf = new SurveyField();
        sf.setHarvest(harvestRepository.findById(new Long(harvestId)).get());
        sf.setField(fieldRepository.findById(new Long(fieldId)).get());
        sf.setName(name);
        sf.setDateData(new DateData(this.dateFormatter(sowedDate), this.dateFormatter(emergenceDate), this.dateFormatter(harvestDate)));
        sf.setProductivityData(new ProductivityData(productivityField, productivityFarmer, separatedWeight));
        sf.setLocationData(new LocationData(longitude, latitute));
        sf.setQuestionData(new QuestionData(rustResistant, bt));
        sf.setSizeData(new SizeData(totalArea, totalPlantedArea, plantPerMeter));
        
        surveyFieldRepository.save(sf);
        
        return this.environment.getProperty("app.view.route.create.success.survey.survey-field");
    }
    
    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int surveyFieldId) {
        
        surveyFieldRepository.deleteById(new Long(surveyFieldId));
        
        return this.environment.getProperty("app.view.route.delete.success.survey.survey-field");
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
