package br.edu.utfpr.cp.emater.mip.survey.harvest;

import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.HarvestRepository;
import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.Harvest;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith (SpringRunner.class)
public class HarvestRepositoryTest {
    
    @Autowired
    private HarvestRepository repository;
    
    @Test
    public void findAll() {
        Harvest h1 = repository.save(new Harvest(null, "Safra 2018/2019", new Date(), new Date(2019, 1, 1)));
        Harvest h2 = repository.save(new Harvest(null, "Safra 2017/2018", new Date(), new Date(2019, 1, 1)));
        Harvest h3 = repository.save(new Harvest(null, "Safra 2016/2017", new Date(), new Date(2019, 1, 1)));
        
        Assertions.assertThat(repository.count()).isEqualTo(3);
        
    }
    
}
