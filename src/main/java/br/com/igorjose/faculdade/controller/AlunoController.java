package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.AlunoDTO;
import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.AlunoNotFound;
import br.com.igorjose.faculdade.exceptions.CursoNotFound;
import br.com.igorjose.faculdade.models.Aluno;
import br.com.igorjose.faculdade.repository.AlunoRepository;
import br.com.igorjose.faculdade.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public MessageDTO createAlunoByCurso(@RequestBody @Valid AlunoDTO alunoDTO) {
        return this.alunoService.createAluno(alunoDTO);
    }

    @GetMapping
    public List<Aluno> getAlunos() {
        return this.alunoService.getAllAlunos();
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) throws AlunoNotFound  {
        return this.alunoService.findAlunoById(id);
    }

    @PutMapping("/{id}/edit")
    public MessageDTO updateAluno(@PathVariable Long id, @RequestBody @Valid AlunoDTO alunoDTO) throws AlunoNotFound  {
        return this.alunoService.updateAluno(id, alunoDTO);
    }

    @DeleteMapping("/{id}/delete")
    public MessageDTO deleteAluno(@PathVariable Long id) throws AlunoNotFound  {
        return this.alunoService.deleteAluno(id);
    }

}
