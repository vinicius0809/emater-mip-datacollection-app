package br.edu.utfpr.cp.emater.midmipsystem.domain.base;

        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
