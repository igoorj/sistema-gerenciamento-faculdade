package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.FaculdadeDTO;
import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.FaculdadeNotFound;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.models.Faculdade;
import br.com.igorjose.faculdade.repository.FaculdadeRepository;
import br.com.igorjose.faculdade.service.FaculdadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeController {

    private FaculdadeService faculdadeService;

    @Autowired
    public FaculdadeController(FaculdadeService faculdadeService) {
        this.faculdadeService = faculdadeService;
    }

    @PostMapping
    public MessageDTO cadastrarFaculdade(@RequestBody @Valid FaculdadeDTO faculdadeDTO) {
        return this.faculdadeService.createFaculdade(faculdadeDTO);
    }

    @GetMapping
    public List<Faculdade> getFaculdades() {
        return this.faculdadeService.getAllFaculdades();
    }

    @GetMapping("/{id}")
    public Faculdade getFaculdadeById(@PathVariable Long id) throws FaculdadeNotFound {
        return this.faculdadeService.getFaculdadeById(id);
    }

    @PutMapping("/{id}")
    public MessageDTO updateFaculdade(@PathVariable Long id, @RequestBody @Valid FaculdadeDTO faculdadeDTO) throws FaculdadeNotFound {
        return this.faculdadeService.updateFaculdade(id, faculdadeDTO);
    }


    @DeleteMapping("/{id}")
    public MessageDTO deleteFaculdade(@PathVariable Long id) throws FaculdadeNotFound{
        return this.faculdadeService.deleteFaculdade(id);
    }

}
