package br.edu.utfpr.cp.emater.mip.field.macroregion;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@RunWith (SpringRunner.class)
public class MacroRegionRepositoryTest {
    
    @Autowired
    private MacroRegionRepository repository;
    
    @Test
    public void findAll() {
        repository.save(new MacroRegion(null, "Macro Noroeste"));
        repository.save(new MacroRegion(null, "Macro Norte"));
        repository.save(new MacroRegion(null, "Macro Oeste"));
        repository.save(new MacroRegion(null, "Macro Sul"));
        
        Assertions.assertThat(repository.count()).isEqualTo(4);
    }
}
