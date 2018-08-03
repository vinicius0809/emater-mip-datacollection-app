package br.edu.utfpr.cp.emater.mip.field.region;

import br.edu.utfpr.cp.emater.mip.domain.field.region.RegionRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.region.Region;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private MacroRegionRepository macroRegionRepository;

    @Test
    public void findAll() {
        MacroRegion mr1 = macroRegionRepository.save(new MacroRegion(null, "Macro Norte"));
        MacroRegion mr2 = macroRegionRepository.save(new MacroRegion(null, "Macro Sul"));

        Region r1 = regionRepository.save(new Region(null, "Cornélio Procópio", mr1));
        Region r2 = regionRepository.save(new Region(null, "Londrina", mr1));

        Region r3 = regionRepository.save(new Region(null, "Dois Vizinhos", mr2));
        Region r4 = regionRepository.save(new Region(null, "Pato Branco", mr2));

        Assertions.assertThat(regionRepository.count()).isEqualTo(4);
    }

    @Test
    public void findById() {
        MacroRegion mr1 = macroRegionRepository.save(new MacroRegion(null, "Macro Norte"));
        MacroRegion mr2 = macroRegionRepository.save(new MacroRegion(null, "Macro Sul"));

        Region r1 = regionRepository.save(new Region(null, "Cornélio Procópio", mr1));
        Region r2 = regionRepository.save(new Region(null, "Londrina", mr1));

        Region r3 = regionRepository.save(new Region(null, "Dois Vizinhos", mr2));
        Region r4 = regionRepository.save(new Region(null, "Pato Branco", mr2));

        Assertions.assertThat(regionRepository.findById(r1.getId()).get().getName()).isEqualTo(r1.getName());
        Assertions.assertThat(regionRepository.findById(r2.getId()).get().getName()).isEqualTo(r2.getName());
        Assertions.assertThat(regionRepository.findById(r3.getId()).get().getName()).isEqualTo(r3.getName());
        Assertions.assertThat(regionRepository.findById(r4.getId()).get().getName()).isEqualTo(r4.getName());
    }
}
