package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.CursoNotFound;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<Curso> listCursos() {

        List<Curso> cursosBuscados = this.cursoRepository.findAll();
        return cursosBuscados;
    }

    @PostMapping
    public MessageDTO cadastroCurso(@RequestBody Curso curso) {

        this.cursoRepository.save(curso);
        return MessageDTO
                .builder()
                .message("Curso Cadastrado com sucesso!")
                .build();
    }

    @GetMapping("/{id}")
    public Curso buscaCursoPorId(@PathVariable Long id) throws CursoNotFound {

        Curso cursoSelecionado = verifyIfExistsCurso(id);
        return cursoSelecionado;
    }

    @PutMapping("/{id}/edit")
    public MessageDTO updateCursoById(@PathVariable Long id, @RequestBody Curso curso) throws CursoNotFound {

        Curso cursoToUpdate = verifyIfExistsCurso(id);

        if(cursoToUpdate != null) {
            cursoToUpdate.setNome(curso.getNome());
            cursoToUpdate.setDisciplinaList(curso.getDisciplinaList());
            this.cursoRepository.save(cursoToUpdate);

            return MessageDTO
                    .builder()
                    .message("Curso atualizado com sucesso!")
                    .build();
        }

        return MessageDTO
                .builder()
                .message("Curso não encontrado!")
                .build();
    }

    @DeleteMapping("/{id}/delete")
    public MessageDTO deleteCurso(@PathVariable Long id) throws CursoNotFound{

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

    public Curso verifyIfExistsCurso(Long id) throws CursoNotFound{

        return this.cursoRepository
                .findById(id)
                .orElseThrow(() -> new CursoNotFound());
    }
}
