package br.com.igorjose.faculdade.repository;

import br.com.igorjose.faculdade.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
