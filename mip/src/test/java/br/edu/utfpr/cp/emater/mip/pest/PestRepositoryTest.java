package br.edu.utfpr.cp.emater.mip.pest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PestRepositoryTest {

    @Autowired
    private PestRepository repository;

    @Test
    public void findAll() {
        Pest p1 = repository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.GREATER_15CM));
        Pest p2 = repository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.SMALLER_15CM));
        Pest p3 = repository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.GREATER_15CM));
        Pest p4 = repository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.SMALLER_15CM));

        Assertions.assertThat(repository.count()).isEqualTo(4);
    }

    @Test
    public void findById() {
        Pest p1 = repository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.GREATER_15CM));
        Pest p2 = repository.save(new Pest(null, "Lagarta-da-soja", "Anticarsia gemmatalis", PestSize.SMALLER_15CM));
        Pest p3 = repository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.GREATER_15CM));
        Pest p4 = repository.save(new Pest(null, "Falsa-medideira", "Chrysodeixis spp.", PestSize.SMALLER_15CM));

        Assertions.assertThat(repository.findById(p1.getId()).get().getUsualName()).isEqualTo(p1.getUsualName());
        Assertions.assertThat(repository.findById(p2.getId()).get().getUsualName()).isEqualTo(p2.getUsualName());
        Assertions.assertThat(repository.findById(p3.getId()).get().getUsualName()).isEqualTo(p3.getUsualName());
        Assertions.assertThat(repository.findById(p4.getId()).get().getUsualName()).isEqualTo(p4.getUsualName());
    }
}
