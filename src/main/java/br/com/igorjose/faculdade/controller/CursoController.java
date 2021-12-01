package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.repository.CursoRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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
    public Curso buscaCursoPorId(@PathVariable Long id) {

        Optional<Curso> curso = this.cursoRepository.findById(id);

        if(curso.isPresent()) {
            return curso.get();
        }
        return null;
    }

    @PutMapping("/{id}/edit")
    public MessageDTO updateCursoById(@PathVariable Long id, @RequestBody Curso curso) {

        Curso cursoToUpdate = this.cursoRepository.findById(id).get();

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
    public MessageDTO deleteCurso(@PathVariable Long id) {

        Optional<Curso> cursoToDelete = this.cursoRepository.findById(id);

        if(cursoToDelete.isPresent()) {

            this.cursoRepository.delete(cursoToDelete.get());
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
}
