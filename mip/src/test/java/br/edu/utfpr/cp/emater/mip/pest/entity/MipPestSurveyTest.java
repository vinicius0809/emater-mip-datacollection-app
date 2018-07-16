package br.edu.utfpr.cp.emater.mip.pest.entity;

import br.edu.utfpr.cp.emater.mip.field.area.City;
import br.edu.utfpr.cp.emater.mip.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.field.field.Field;
import br.edu.utfpr.cp.emater.mip.field.area.MacroRegion;
import br.edu.utfpr.cp.emater.mip.field.area.Region;
import br.edu.utfpr.cp.emater.mip.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.survey.entity.DateData;
import br.edu.utfpr.cp.emater.mip.survey.entity.Harvest;
import br.edu.utfpr.cp.emater.mip.survey.entity.LocationData;
import br.edu.utfpr.cp.emater.mip.survey.entity.ProductivityData;
import br.edu.utfpr.cp.emater.mip.survey.entity.QuestionData;
import br.edu.utfpr.cp.emater.mip.survey.entity.SizeData;
import br.edu.utfpr.cp.emater.mip.survey.entity.SurveyField;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class MipPestSurveyTest {
    
    @Test
    public void create() {
        Field field = new Field();
        field.setId(1L);
        field.setName("A nice place to live");
        field.setLocation("Rib. Aquidaban / 362-A");
        field.setFarmer(new Farmer(1L, "Antonio Haffman"));
        field.setCity(new City(1L, "Marialva", new Region(1L, "Maringá", new MacroRegion(1L, "Noroeste"))));
        field.setSupervisors(Stream.of(new Supervisor(1L, "Fabianderson J Baio"), new Supervisor(2L, "Ailton R Poppi")).collect(Collectors.toSet()));
        
        SurveyField surveyField = new SurveyField();
        surveyField.setId(1L);
        surveyField.setName("BMX Potencia");
        surveyField.setDateData(new DateData(new Date(), new Date(), new Date()));
        surveyField.setField(field);
        surveyField.setHarvest(new Harvest(1L, "Safra 2017/2018", new Date(), new Date()));
        surveyField.setLocationData(new LocationData(23.75664, 51.5607));
        surveyField.setProductivityData(new ProductivityData(150.0, 150.0, false));
        surveyField.setQuestionData(new QuestionData(false, false));
        surveyField.setSizeData(new SizeData(22.99, 22.99, 14.0));
        
        MipPestSurvey survey = new MipPestSurvey();
        survey.setId(1L);
        survey.setSurveyField(surveyField);
        survey.addSamplePest(new Date(), 13, 3, GrowthPhase.V1, new Pest(1L, "Lagarta-das-vagens", "Spodoptera spp.", PestSize.GREATER_15CM), 0.1);
    }
    
}
