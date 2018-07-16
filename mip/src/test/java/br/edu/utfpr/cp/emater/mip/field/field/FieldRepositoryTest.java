package br.edu.utfpr.cp.emater.mip.field.field;

import br.edu.utfpr.cp.emater.mip.field.city.City;
import br.edu.utfpr.cp.emater.mip.field.city.CityRepository;
import br.edu.utfpr.cp.emater.mip.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.field.person.Farmer;
import br.edu.utfpr.cp.emater.mip.field.person.FarmerRepository;
import br.edu.utfpr.cp.emater.mip.field.person.Supervisor;
import br.edu.utfpr.cp.emater.mip.field.person.SupervisorRepository;
import br.edu.utfpr.cp.emater.mip.field.region.Region;
import br.edu.utfpr.cp.emater.mip.field.region.RegionRepository;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FieldRepositoryTest {

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

    @Test
    public void findAll() {
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

        Field fi1 = fieldRepository.save(new Field(null, "A nice place to live", "Road to hope", c1, f1, Stream.of(s1, s2).collect(Collectors.toSet())));
        Field fi2 = fieldRepository.save(new Field(null, "A nice place to work", "Road to energy", c2, f2, Stream.of(s3, s4).collect(Collectors.toSet())));
        Field fi3 = fieldRepository.save(new Field(null, "A good place to enjoy", "Road to hapiness", c1, f3, Collections.singleton(s1)));
        Field fi4 = fieldRepository.save(new Field(null, "A good place to travel", "Road to more hapiness", c3, f4, Collections.singleton(s4)));

        Assertions.assertThat(fieldRepository.count()).isEqualTo(4);
    }

    @Test
    public void findById() {
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

        Field fi1 = fieldRepository.save(new Field(null, "A nice place to live", "Road to hope", c1, f1, Stream.of(s1, s2).collect(Collectors.toSet())));
        Field fi2 = fieldRepository.save(new Field(null, "A nice place to work", "Road to energy", c2, f2, Stream.of(s3, s4).collect(Collectors.toSet())));
        Field fi3 = fieldRepository.save(new Field(null, "A good place to enjoy", "Road to hapiness", c1, f3, Collections.singleton(s1)));
        Field fi4 = fieldRepository.save(new Field(null, "A good place to travel", "Road to more hapiness", c3, f4, Collections.singleton(s4)));

        Assertions.assertThat(fieldRepository.findById(fi1.getId()).get().getName()).isEqualTo(fi1.getName());
        Assertions.assertThat(fieldRepository.findById(fi2.getId()).get().getName()).isEqualTo(fi2.getName());
        Assertions.assertThat(fieldRepository.findById(fi3.getId()).get().getName()).isEqualTo(fi3.getName());
        Assertions.assertThat(fieldRepository.findById(fi4.getId()).get().getName()).isEqualTo(fi4.getName());
    }
}
