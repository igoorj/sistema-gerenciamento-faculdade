package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.CursoNotFound;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listCursos() {
        return this.cursoService.getAllCursos();
    }

    @PostMapping
    public MessageDTO cadastroCurso(@RequestBody Curso curso) {

        return this.cursoService.createCurso(curso);
    }

    @GetMapping("/{id}")
    public Curso buscaCursoPorId(@PathVariable Long id) throws CursoNotFound {

        return this.cursoService.getById(id);
    }

    @PutMapping("/{id}/edit")
    public MessageDTO updateCursoById(@PathVariable Long id, @RequestBody Curso curso) throws CursoNotFound {

        return this.cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("/{id}/delete")
    public MessageDTO deleteCurso(@PathVariable Long id) throws CursoNotFound{

        return this.cursoService.deleteCurso(id);
    }

}
