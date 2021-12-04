package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.AlunoNotFound;
import br.com.igorjose.faculdade.exceptions.CursoNotFound;
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
    public Aluno getAlunoById(@PathVariable Long id) throws AlunoNotFound  {

        Aluno aluno = verifyIsExistsAluno(id);
        return aluno;
    }

    @PutMapping("/{id}/edit")
    public MessageDTO updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) throws AlunoNotFound  {

        Aluno alunoToUpdate = verifyIsExistsAluno(id);

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
    public MessageDTO deleteAluno(@PathVariable Long id) throws AlunoNotFound  {

        Aluno alunoToDelete = verifyIsExistsAluno(id);
        if(alunoToDelete != null ) {
            this.alunoRepository.delete(alunoToDelete);
            return MessageDTO.builder().message("Aluno deletado com sucesso").build();
        }
        return MessageDTO.builder().message("Id não identificado").build();
    }

    public Aluno verifyIsExistsAluno(Long id) throws AlunoNotFound {

        return this.alunoRepository
                .findById(id)
                .orElseThrow(() -> new AlunoNotFound());
    }
}
