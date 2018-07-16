package br.edu.utfpr.cp.emater.mip.field.person;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class SupervisorRepositoryTest {

    @Autowired
    private SupervisorRepository repository;

    @Test
    public void findAll() {
        Supervisor f1 = repository.save(new Supervisor(null, "John Allan"));
        Supervisor f2 = repository.save(new Supervisor(null, "Anna Allan"));
        Supervisor f3 = repository.save(new Supervisor(null, "Michael Allan"));
        Supervisor f4 = repository.save(new Supervisor(null, "Andrew Allan"));

        Assertions.assertThat(repository.count()).isEqualTo(4);
    }

    @Test
    public void findById() {
        Supervisor f1 = repository.save(new Supervisor(null, "John Allan"));
        Supervisor f2 = repository.save(new Supervisor(null, "Anna Allan"));
        Supervisor f3 = repository.save(new Supervisor(null, "Michael Allan"));
        Supervisor f4 = repository.save(new Supervisor(null, "Andrew Allan"));

        Assertions.assertThat(repository.findById(f1.getId()).get().getName()).isEqualTo(f1.getName());
        Assertions.assertThat(repository.findById(f2.getId()).get().getName()).isEqualTo(f2.getName());
        Assertions.assertThat(repository.findById(f3.getId()).get().getName()).isEqualTo(f3.getName());
        Assertions.assertThat(repository.findById(f4.getId()).get().getName()).isEqualTo(f4.getName());
    }

}
