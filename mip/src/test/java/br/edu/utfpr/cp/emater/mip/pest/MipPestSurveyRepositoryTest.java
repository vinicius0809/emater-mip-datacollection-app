package br.edu.utfpr.cp.emater.mip.pest;

import br.edu.utfpr.cp.emater.mip.field.city.City;
import br.edu.utfpr.cp.emater.mip.field.city.CityRepository;
import br.edu.utfpr.cp.emater.mip.field.field.Field;
import br.edu.utfpr.cp.emater.mip.field.field.FieldRepository;
import br.edu.utfpr.cp.emater.mip.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.field.person.FarmerRepository;
import br.edu.utfpr.cp.emater.mip.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.field.person.SupervisorRepository;
import br.edu.utfpr.cp.emater.mip.field.region.Region;
import br.edu.utfpr.cp.emater.mip.field.region.RegionRepository;
import br.edu.utfpr.cp.emater.mip.survey.harvest.Harvest;
import br.edu.utfpr.cp.emater.mip.survey.harvest.HarvestRepository;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.DateData;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.LocationData;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.ProductivityData;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.QuestionData;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.SizeData;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.SurveyField;
import br.edu.utfpr.cp.emater.mip.survey.surveyfield.SurveyFieldRepository;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MipPestSurveyRepositoryTest {

    @Autowired
    private MipPestSurveyRepository mipPestSurveyRepository;

    @Autowired
    private SurveyFieldRepository surveyFieldRepository;

    @Autowired
    private HarvestRepository harvestRepository;

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
    private PestRepository pestRepository;

    @Test
    public void findAll() {
        Harvest h1 = harvestRepository.save(new Harvest(null, "Safra 2018/2019", new Date(), new Date(2019, 1, 1)));

        MacroRegion mr1 = macroRegionRepository.save(new MacroRegion(null, "Macro Norte"));

        Region r1 = regionRepository.save(new Region(null, "Cornélio Procópio", mr1));

        City c1 = cityRepository.save(new City(null, "Londrina", r1));

        Farmer f1 = farmerRepository.save(new Farmer(null, "John Doe"));

        Supervisor s1 = supervisorRepository.save(new Supervisor(null, "John Allan"));
        Supervisor s2 = supervisorRepository.save(new Supervisor(null, "Anna Allan"));
        Supervisor s3 = supervisorRepository.save(new Supervisor(null, "Michael Allan"));
        Supervisor s4 = supervisorRepository.save(new Supervisor(null, "Andrew Allan"));

        Field field = new Field(null, "A nice place to live", "Road to hope", c1, f1, null);
        field.addSupervisor(s1);
        field.addSupervisor(s2);

        Field fi1 = fieldRepository.save(field);

        SurveyField sf1 = surveyFieldRepository.save(new SurveyField(null, "BMX Potencia 1", new QuestionData(true, true), new DateData(new Date(), new Date(), new Date()), new SizeData(1.5, 1.5, 1.5), new LocationData(1.5, 1.5), new ProductivityData(1.5, 1.5, true), fi1, h1));
        SurveyField sf2 = surveyFieldRepository.save(new SurveyField(null, "BMX Potencia 2", new QuestionData(false, false), new DateData(new Date(), new Date(), new Date()), new SizeData(2.5, 2.5, 2.5), new LocationData(2.5, 2.5), new ProductivityData(2.5, 2.5, true), fi1, h1));
        SurveyField sf3 = surveyFieldRepository.save(new SurveyField(null, "BMX Potencia 3", new QuestionData(true, false), new DateData(new Date(), new Date(), new Date()), new SizeData(3.5, 3.5, 3.5), new LocationData(3.5, 3.5), new ProductivityData(3.5, 3.5, true), fi1, h1));

        Pest p1 = pestRepository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.GREATER_15CM));
        Pest p2 = pestRepository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.SMALLER_15CM));
        Pest p3 = pestRepository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.GREATER_15CM));
        Pest p4 = pestRepository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.SMALLER_15CM));

        MipPestSurvey mip1 = new MipPestSurvey(null, sf1, null);
        mip1.addSamplePest(new Date(), 10, 13, GrowthPhase.R2, p1, 1.5);
        mip1.addSamplePest(new Date(), 11, 14, GrowthPhase.R2, p2, 2.5);
        mip1.addSamplePest(new Date(), 12, 15, GrowthPhase.R2, p3, 3.5);
        mipPestSurveyRepository.save(mip1);
        
        MipPestSurvey mip2 = new MipPestSurvey(null, sf2, null);
        mip2.addSamplePest(new Date(), 10, 13, GrowthPhase.R2, p2, 1.5);
        mip2.addSamplePest(new Date(), 11, 14, GrowthPhase.R2, p3, 2.5);
        mip2.addSamplePest(new Date(), 12, 15, GrowthPhase.R2, p4, 3.5);
        mipPestSurveyRepository.save(mip2);
        
        MipPestSurvey mip3 = new MipPestSurvey(null, sf3, null);
        mip3.addSamplePest(new Date(), 11, 14, GrowthPhase.R2, p1, 1.5);
        mip3.addSamplePest(new Date(), 12, 15, GrowthPhase.R2, p3, 2.5);
        mip3.addSamplePest(new Date(), 13, 16, GrowthPhase.R2, p4, 3.5);
        mipPestSurveyRepository.save(mip3);
        
        Assertions.assertThat(mipPestSurveyRepository.count()).isEqualTo(3);
        
    }
}
