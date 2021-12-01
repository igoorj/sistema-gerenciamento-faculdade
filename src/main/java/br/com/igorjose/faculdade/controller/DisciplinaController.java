package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.models.Disciplina;
import br.com.igorjose.faculdade.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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
    public Disciplina findDisciplina(@PathVariable Long id) {
        Optional<Disciplina> disciplinaRecuperada = this.disciplinaRepository.findById(id);
        return disciplinaRecuperada.get();
    }

    // update by id

    @PutMapping("/{id}/edit")
    public MessageDTO updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {

        Disciplina disciplinaToUpdate = this.disciplinaRepository.findById(id).get();

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
    public MessageDTO deleteDisciplina(@PathVariable Long id) {

        Disciplina disciplinaToDelete = this.disciplinaRepository.findById(id).get();

        if(disciplinaToDelete != null) {
            this.disciplinaRepository.delete(disciplinaToDelete);

            return MessageDTO
                    .builder()
                    .message("Disciplina deletada com sucesso!")
                    .build();
        }
        return null;
    }
}
