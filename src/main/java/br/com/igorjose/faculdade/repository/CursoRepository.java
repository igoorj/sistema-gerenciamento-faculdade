package br.com.igorjose.faculdade.repository;

import br.com.igorjose.faculdade.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long > {
}
