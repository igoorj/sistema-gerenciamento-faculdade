package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.models.Aluno;
import br.com.igorjose.faculdade.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @PostMapping
    public MessageDTO createAlunoByCurso(@RequestBody Aluno aluno) {
        this.alunoRepository.save(aluno);
        return MessageDTO
                .builder()
                .message("Aluno cadastrado com sucesso!")
                .build();
    }

    @GetMapping
    public List<Aluno> getAlunos() {
        List<Aluno> alunos = this.alunoRepository.findAll();
        return alunos;
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        Optional<Aluno> aluno = this.alunoRepository.findById(id);
        if(aluno.isPresent()) {
            return aluno.get();
        }
        return null;
    }

    @PutMapping("/{id}/edit")
    public MessageDTO updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno alunoToUpdate = this.alunoRepository.findById(id).get();
        if(alunoToUpdate != null) {
            alunoToUpdate.setNome(aluno.getNome());
            alunoToUpdate.setCpf(aluno.getCpf());
            alunoToUpdate.setDataNascimento(aluno.getDataNascimento());

            this.alunoRepository.save(alunoToUpdate);
            return MessageDTO.builder().message("Aluno atualizado com sucesso!").build();
        }
        return MessageDTO.builder().message("Id não identificado").build();
    }

    @DeleteMapping("/{id}/delete")
    public MessageDTO deleteAluno(@PathVariable Long id) {
        Optional<Aluno> alunoToDelete = this.alunoRepository.findById(id);
        if(alunoToDelete.isPresent()) {
            this.alunoRepository.delete(alunoToDelete.get());
            return MessageDTO.builder().message("Aluno deletado com sucesso").build();
        }
        return MessageDTO.builder().message("Id não identificado").build();
    }
}
