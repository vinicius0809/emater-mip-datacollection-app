package br.edu.utfpr.cp.emater.mip.field.city;

import br.edu.utfpr.cp.emater.mip.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.field.region.Region;
import br.edu.utfpr.cp.emater.mip.field.region.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CityRepositoryTest {

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

        Assertions.assertThat(cityRepository.count()).isEqualTo(4);
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

        Assertions.assertThat(cityRepository.findById(c1.getId()).get().getName()).isEqualTo(c1.getName());
        Assertions.assertThat(cityRepository.findById(c2.getId()).get().getName()).isEqualTo(c2.getName());
        Assertions.assertThat(cityRepository.findById(c3.getId()).get().getName()).isEqualTo(c3.getName());
        Assertions.assertThat(cityRepository.findById(c4.getId()).get().getName()).isEqualTo(c4.getName());
    }

}
