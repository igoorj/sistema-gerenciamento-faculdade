package br.com.igorjose.faculdade.service;

import java.util.List;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.CursoNotFound;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getAllCursos() {
        return this.cursoRepository.findAll();
    }

    public Curso getById(Long id) throws CursoNotFound {
        Curso curso = verifyIfExistsCurso(id);
        return curso;
    }

    public MessageDTO createCurso(Curso curso) {
        this.cursoRepository.save(curso);
        return MessageDTO
                .builder()
                .message("Curso cadastrado com sucesso!")
                .build();
    }
    public MessageDTO updateCurso(Long id, Curso curso) throws CursoNotFound {
        Curso cursoToUpdate = verifyIfExistsCurso(id);

        if(cursoToUpdate != null) {
            cursoToUpdate.setNome(curso.getNome());
            cursoToUpdate.setDisciplinaList(curso.getDisciplinaList());
            cursoToUpdate.setAlunosMatriculados(curso.getAlunosMatriculados());

            this.cursoRepository.save(cursoToUpdate);
            return MessageDTO
                    .builder()
                    .message("Curso atualizado com sucesso!")
                    .build();
        }
        return MessageDTO
                .builder()
                .message("Curso não encontrado")
                .build();
    }

    public MessageDTO deleteCurso(Long id) throws CursoNotFound {
        Curso cursoToDelete = verifyIfExistsCurso(id);
        if(cursoToDelete != null) {
            this.cursoRepository.delete(cursoToDelete);
            return MessageDTO
                    .builder()
                    .message("Curso deletado com sucesso!")
                    .build();
        }
        return MessageDTO
                .builder()
                .message("Curso não encontrado!")
                .build();
    }

    public Curso verifyIfExistsCurso(Long id) throws CursoNotFound {
        return this.cursoRepository
                .findById(id)
                .orElseThrow(() -> new CursoNotFound());
    }

}
