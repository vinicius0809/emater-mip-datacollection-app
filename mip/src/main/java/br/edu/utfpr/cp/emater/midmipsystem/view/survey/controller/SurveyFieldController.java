package br.edu.utfpr.cp.emater.midmipsystem.view.survey.controller;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Field;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.FieldRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.HarvestRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.DateData;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.LocationData;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.ProductivityData;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.QuestionData;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.SizeData;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.SurveyField;
import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.SurveyFieldRepository;
import br.edu.utfpr.cp.emater.midmipsystem.view.survey.controller.HarvestController;
import br.edu.utfpr.cp.emater.midmipsystem.view.survey.dto.SurveyFieldDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/survey-field")
public class SurveyFieldController {

    private final SurveyFieldRepository surveyFieldRepository;
    private final FieldRepository fieldRepository;
    private final HarvestRepository harvestRepository;

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
    public SurveyFieldController(SurveyFieldRepository surveyFieldRepository, FieldRepository fieldRepository,
            HarvestRepository harvestRepository, Environment environment) {
        this.surveyFieldRepository = surveyFieldRepository;
        this.fieldRepository = fieldRepository;
        this.harvestRepository = harvestRepository;
        this.environment = environment;
        this.operationSuccessMessage = false;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findAll(Model data) {
        data.addAttribute("surveyFields", surveyFieldRepository.findAll());
        data.addAttribute("harvests", harvestRepository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();

        data.addAttribute("urlCreate", this.environment.getProperty("app.view.route.create.survey.survey-field"));
        data.addAttribute("urlUpdate", this.environment.getProperty("app.view.route.update.survey.survey-field"));
        data.addAttribute("urlDelete", this.environment.getProperty("app.view.route.delete.survey.survey-field"));

        return this.environment.getProperty("app.view.route.template.main.survey.survey-field");
    }

    private List<Field> retrieveFieldsAssignedToSelectedHarvest(Long harvestId) {
        return surveyFieldRepository.findAll().stream()
                .filter(sf -> sf.getHarvest().equals(harvestRepository.findById(harvestId).orElseThrow()))
                .map(SurveyField::getField).collect(Collectors.toList());
    }

    private List<Field> retrieveFieldsNotAssignedToSelectedHarvestSurvey(Long harvestId) {

        List<Field> fieldsAlreadyAssigned = this.retrieveFieldsAssignedToSelectedHarvest(harvestId);
        List<Field> allFields = fieldRepository.findAll();

        allFields.removeAll(fieldsAlreadyAssigned);

        return allFields;
    }

    @RequestMapping(value = "/select-field", method = RequestMethod.POST)
    public String selectFieldForSurvey(@RequestParam int harvestId, Model data) {
        data.addAttribute("fields", this.retrieveFieldsNotAssignedToSelectedHarvestSurvey(new Long(harvestId)));
        data.addAttribute("harvestId", harvestId);

        return this.environment.getProperty("app.view.route.template.select-field.survey.survey-field");
    }

    @RequestMapping(value = "/field-form", method = RequestMethod.GET)
    public String surveyFieldForm(@RequestParam int fieldId, @RequestParam int harvestId, Model data) {
        data.addAttribute("harvestId", harvestId);

        Field selectedField = fieldRepository.findById(new Long(fieldId)).orElseThrow();

        data.addAttribute("selectedField", selectedField);

        data.addAttribute("pageTitle",
                String.format("Dados da UR '%s' (%s, %s) para %s", selectedField.getName(),
                        selectedField.getFarmer().getName(), selectedField.getCity().getName(),
                        harvestRepository.findById(new Long(harvestId)).orElseThrow().getName()));

        return this.environment.getProperty("app.view.route.template.form.survey.survey-field");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(SurveyFieldDTO surveyField) {

        SurveyField sf = new SurveyField();
        sf.setHarvest(harvestRepository.findById(new Long(surveyField.getHarvestId())).orElseThrow());
        sf.setField(fieldRepository.findById(new Long(surveyField.getFieldId())).orElseThrow());
        sf.setSeedName(surveyField.getSeedName());
        sf.setSporeCollectorPresent(surveyField.isSporeCollector());
        sf.setDateData(new DateData(this.dateFormatter(surveyField.getSowedDate()),
                this.dateFormatter(surveyField.getEmergenceDate()), this.dateFormatter(surveyField.getHarvestDate())));
        sf.setProductivityData(new ProductivityData(surveyField.getProductivityField(), surveyField.getProductivityFarmer(), surveyField.isSeparatedWeight()));
        sf.setLocationData(new LocationData(surveyField.getLongitude(), surveyField.getLatitute()));
        sf.setQuestionData(new QuestionData(surveyField.isRustResistant(), surveyField.isBt()));
        sf.setSizeData(new SizeData(surveyField.getTotalArea(), surveyField.getTotalPlantedArea(), surveyField.getPlantPerMeter()));

        surveyFieldRepository.save(sf);

        this.setOperationSuccessMessage();

        return this.environment.getProperty("app.view.route.create.success.survey.survey-field");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int surveyFieldId) {

        surveyFieldRepository.deleteById(new Long(surveyFieldId));

        this.setOperationSuccessMessage();

        return this.environment.getProperty("app.view.route.delete.success.survey.survey-field");
    }

    private Date dateFormatter(String date) {
        Date theDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        try {
            theDate = formatter.parse(date);
            return theDate;

        } catch (ParseException ex) {
            theDate = new Date();
            // Logger.getLogger(HarvestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return theDate;
    }

}
