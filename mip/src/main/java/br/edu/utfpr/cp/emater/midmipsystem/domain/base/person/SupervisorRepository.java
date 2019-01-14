package br.edu.utfpr.cp.emater.midmipsystem.domain.base.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    
}
