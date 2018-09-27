package br.edu.utfpr.cp.emater.mip;

import br.edu.utfpr.cp.emater.mip.domain.field.city.City;
import br.edu.utfpr.cp.emater.mip.domain.field.city.CityRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.field.Field;
import br.edu.utfpr.cp.emater.mip.domain.field.field.FieldRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.domain.field.person.FarmerRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.domain.field.person.SupervisorRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.region.Region;
import br.edu.utfpr.cp.emater.mip.domain.field.region.RegionRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.GrowthPhase;
import br.edu.utfpr.cp.emater.mip.domain.pest.MipPestSurvey;
import br.edu.utfpr.cp.emater.mip.domain.pest.MipPestSurveyRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.Pest;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestSize;
import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.Harvest;
import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.HarvestRepository;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.DateData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.LocationData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.ProductivityData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.QuestionData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SizeData;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SurveyField;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SurveyFieldRepository;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MipApplication {

    public static void main(String[] args) {
        SpringApplication.run(MipApplication.class, args);
    }
}

@Component
class CLR implements CommandLineRunner {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private MacroRegionRepository macroRegionRepository;

    @Autowired
    private SurveyFieldRepository surveyFieldRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private MipPestSurveyRepository mipPestSurveyRepository;

    @Autowired
    private PestRepository pestRepository;

    @Override
    public void run(String... args) throws Exception {
        MacroRegion mr1 = macroRegionRepository.save(new MacroRegion(null, "Macro Norte"));
        MacroRegion mr2 = macroRegionRepository.save(new MacroRegion(null, "Macro Sul"));

        Region r1 = regionRepository.save(new Region(null, "Cornélio Procópio", mr1));
        Region r2 = regionRepository.save(new Region(null, "Dois Vizinhos", mr2));

        City c1 = cityRepository.save(new City(null, "Londrina", r1));
        City c2 = cityRepository.save(new City(null, "Cornélio Procópio", r1));
        City c3 = cityRepository.save(new City(null, "Salto do Lontra", r2));
        City c4 = cityRepository.save(new City(null, "Nova Prata do Iguaçú", r2));

        Farmer f1 = farmerRepository.save(new Farmer(null, "John Doe"));
        Farmer f2 = farmerRepository.save(new Farmer(null, "Anna Doe"));
        Farmer f3 = farmerRepository.save(new Farmer(null, "Michael Doe"));
        Farmer f4 = farmerRepository.save(new Farmer(null, "Andrew Doe"));

        Supervisor s1 = supervisorRepository.save(new Supervisor(null, "John Allan"));
        Supervisor s2 = supervisorRepository.save(new Supervisor(null, "Anna Allan"));
        Supervisor s3 = supervisorRepository.save(new Supervisor(null, "Michael Allan"));
        Supervisor s4 = supervisorRepository.save(new Supervisor(null, "Andrew Allan"));

        Field fi1 = new Field(null, "A nice place to live", "Road to hope", c1, f1, null);
        fi1.addSupervisor(s1);
        fi1.addSupervisor(s2);
        fieldRepository.save(fi1);

        Field fi2 = fieldRepository.save(new Field(null, "A nice place to work", "Road to energy", c2, f2, Stream.of(s3, s4).collect(Collectors.toList())));
        Field fi3 = fieldRepository.save(new Field(null, "A good place to enjoy", "Road to hapiness", c1, f3, Collections.singletonList(s1)));

        Harvest h1 = harvestRepository.save(new Harvest(null, "Safra 2018/2019", new Date(), new Date(2019, 1, 1)));

        SurveyField sf1 = surveyFieldRepository.save(new SurveyField(null, "BMX Potencia 1", new QuestionData(true, true), new DateData(new Date(), new Date(), new Date()), new SizeData(1.5, 1.5, 1.5), new LocationData(1.5, 1.5), new ProductivityData(1.5, 1.5, true), fi1, h1));
        SurveyField sf2 = surveyFieldRepository.save(new SurveyField(null, "BMX Potencia 2", new QuestionData(false, false), new DateData(new Date(), new Date(), new Date()), new SizeData(2.5, 2.5, 2.5), new LocationData(2.5, 2.5), new ProductivityData(2.5, 2.5, true), fi2, h1));
        SurveyField sf3 = surveyFieldRepository.save(new SurveyField(null, "BMX Potencia 3", new QuestionData(true, false), new DateData(new Date(), new Date(), new Date()), new SizeData(3.5, 3.5, 3.5), new LocationData(3.5, 3.5), new ProductivityData(3.5, 3.5, true), fi3, h1));

        Pest p1 = pestRepository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.GREATER_15CM));
        Pest p2 = pestRepository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.SMALLER_15CM));
        Pest p3 = pestRepository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.GREATER_15CM));
        Pest p4 = pestRepository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.SMALLER_15CM));

        MipPestSurvey mip1 = new MipPestSurvey(null, sf1, null);
//        mip1.addSamplePest(new Date(), 10, 13, GrowthPhase.R2, p1, 1.5);
//        mip1.addSamplePest(new Date(), 11, 14, GrowthPhase.R2, p2, 2.5);
//        mip1.addSamplePest(new Date(), 12, 15, GrowthPhase.R2, p3, 3.5);
        mipPestSurveyRepository.save(mip1);

        MipPestSurvey mip2 = new MipPestSurvey(null, sf2, null);
//        mip2.addSamplePest(new Date(), 10, 13, GrowthPhase.R2, p2, 1.5);
//        mip2.addSamplePest(new Date(), 11, 14, GrowthPhase.V1, p3, 2.5);
//        mip2.addSamplePest(new Date(), 12, 15, GrowthPhase.V5, p4, 3.5);
        mipPestSurveyRepository.save(mip2);

        MipPestSurvey mip3 = new MipPestSurvey(null, sf3, null);
//        mip3.addSamplePest(new Date(), 11, 14, GrowthPhase.R2, p1, 1.5);
//        mip3.addSamplePest(new Date(), 12, 15, GrowthPhase.V1, p3, 2.5);
//        mip3.addSamplePest(new Date(), 13, 16, GrowthPhase.V5, p4, 3.5);
        mipPestSurveyRepository.save(mip3);
    }

}
