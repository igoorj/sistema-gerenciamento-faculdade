package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.DisciplinaNotFound;
import br.com.igorjose.faculdade.models.Disciplina;
import br.com.igorjose.faculdade.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaController(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    // list all

    @GetMapping("/")
    public List<Disciplina> getDisciplina() {
        List<Disciplina> disciplinaList = this.disciplinaRepository.findAll();
        return disciplinaList;
    }

    // create method

    @PostMapping("/")
    public MessageDTO cadastroDisciplinas(@RequestBody Disciplina novaDisciplina) {
        this.disciplinaRepository.save(novaDisciplina);
        return MessageDTO
                .builder()
                .message("Disciplina cadastrada com sucesso!")
                .build();
    }

    // get by id

    @GetMapping("/{id}")
    public Disciplina findDisciplina(@PathVariable Long id) throws DisciplinaNotFound {
        Disciplina disciplinaRecuperada = verifyIfExistsDisciplina(id);
        return disciplinaRecuperada;
    }

    // update by id

    @PutMapping("/{id}/edit")
    public MessageDTO updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) throws DisciplinaNotFound {

        Disciplina disciplinaToUpdate = verifyIfExistsDisciplina(id);

        if(disciplinaToUpdate != null) {
            disciplinaToUpdate.setCargaHoraria(disciplina.getCargaHoraria());
            disciplinaToUpdate.setNome(disciplina.getNome());

            this.disciplinaRepository.save(disciplinaToUpdate);

            return MessageDTO
                    .builder()
                    .message("Disciplina atualizada com sucesso!")
                    .build();
        }
        return null;
    }

    @DeleteMapping("/{id}/delete")
    public MessageDTO deleteDisciplina(@PathVariable Long id) throws DisciplinaNotFound {

        Disciplina disciplinaToDelete = verifyIfExistsDisciplina(id);

        if(disciplinaToDelete != null) {
            this.disciplinaRepository.delete(disciplinaToDelete);

            return MessageDTO
                    .builder()
                    .message("Disciplina deletada com sucesso!")
                    .build();
        }
        return null;
    }

    public Disciplina verifyIfExistsDisciplina(Long id) throws DisciplinaNotFound {

        return this.disciplinaRepository
                .findById(id)
                .orElseThrow(() -> new DisciplinaNotFound(id));
    }
}
