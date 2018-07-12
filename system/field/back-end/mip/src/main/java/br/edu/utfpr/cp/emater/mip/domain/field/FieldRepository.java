package br.edu.utfpr.cp.emater.mip.domain.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FieldRepository extends JpaRepository<Field, Long> {}