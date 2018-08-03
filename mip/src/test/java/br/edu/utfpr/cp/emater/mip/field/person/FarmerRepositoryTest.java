package br.edu.utfpr.cp.emater.mip.field.person;

import br.edu.utfpr.cp.emater.mip.domain.field.person.FarmerRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.person.Farmer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FarmerRepositoryTest {

    @Autowired
    private FarmerRepository repository;

    @Test
    public void findAll() {
        Farmer f1 = repository.save(new Farmer(null, "John Doe"));
        Farmer f2 = repository.save(new Farmer(null, "Anna Doe"));
        Farmer f3 = repository.save(new Farmer(null, "Michael Doe"));
        Farmer f4 = repository.save(new Farmer(null, "Andrew Doe"));

        Assertions.assertThat(repository.count()).isEqualTo(4);
    }

    @Test
    public void findById() {
        Farmer f1 = repository.save(new Farmer(null, "John Doe"));
        Farmer f2 = repository.save(new Farmer(null, "Anna Doe"));
        Farmer f3 = repository.save(new Farmer(null, "Michael Doe"));
        Farmer f4 = repository.save(new Farmer(null, "Andrew Doe"));

        Assertions.assertThat(repository.findById(f1.getId()).get().getName()).isEqualTo(f1.getName());
        Assertions.assertThat(repository.findById(f2.getId()).get().getName()).isEqualTo(f2.getName());
        Assertions.assertThat(repository.findById(f3.getId()).get().getName()).isEqualTo(f3.getName());
        Assertions.assertThat(repository.findById(f4.getId()).get().getName()).isEqualTo(f4.getName());
    }

}
