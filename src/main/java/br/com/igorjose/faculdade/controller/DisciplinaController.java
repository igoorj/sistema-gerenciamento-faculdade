package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.DisciplinaDTO;
import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.DisciplinaNotFound;
import br.com.igorjose.faculdade.models.Disciplina;
import br.com.igorjose.faculdade.repository.DisciplinaRepository;
import br.com.igorjose.faculdade.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/")
    public List<Disciplina> getDisciplina() {

        return this.disciplinaService.listAll();
    }

    @PostMapping("/")
    public MessageDTO cadastroDisciplinas(@RequestBody @Valid DisciplinaDTO novaDisciplinaDTO) {

        return this.disciplinaService.createDisciplina(novaDisciplinaDTO);
    }

    @GetMapping("/{id}")
    public Disciplina findDisciplina(@PathVariable Long id) throws DisciplinaNotFound {

        return this.disciplinaService.findDisciplinaById(id);
    }


    @PutMapping("/{id}/edit")
    public MessageDTO updateDisciplina(@PathVariable Long id, @RequestBody @Valid DisciplinaDTO disciplinaDTO) throws DisciplinaNotFound {

        return this.disciplinaService.updateDisciplina(id, disciplinaDTO);
    }

    @DeleteMapping("/{id}/delete")
    public MessageDTO deleteDisciplina(@PathVariable Long id) throws DisciplinaNotFound {

        return this.disciplinaService.deleteDisciplina(id);
    }

}
