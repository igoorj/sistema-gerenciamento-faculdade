package br.com.igorjose.faculdade.controller;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.models.Curso;
import br.com.igorjose.faculdade.models.Faculdade;
import br.com.igorjose.faculdade.repository.FaculdadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeController {

    private FaculdadeRepository faculdadeRepository;

    @Autowired
    public FaculdadeController(FaculdadeRepository faculdadeRepository) {
        this.faculdadeRepository = faculdadeRepository;
    }

    @PostMapping
    public MessageDTO cadastrarFaculdade(@RequestBody Faculdade faculdade) {

        this.faculdadeRepository.save(faculdade);
        return MessageDTO
                .builder()
                .message("Faculdade Cadastrada com sucesso!")
                .build();
    }

    @GetMapping
    public List<Faculdade> getFaculdades() {
        List<Faculdade> faculdades = this.faculdadeRepository.findAll();
        return faculdades;
    }

    @GetMapping("/{id}")
    public Faculdade getFaculdadeById(@PathVariable Long id) {

        Faculdade faculdadeSelecionada = verifyIfExistsFaculdade(id);
        return faculdadeSelecionada;
    }

    @PutMapping("/{id}")
    public MessageDTO updateFaculdade(@PathVariable Long id, @RequestBody Faculdade faculdade) {

        Faculdade faculdadeToUpdate = verifyIfExistsFaculdade(id);

        if(faculdadeToUpdate != null) {

            faculdadeToUpdate.setCnpj(faculdade.getCnpj());
            faculdadeToUpdate.setCursos(faculdade.getCursos());
            faculdadeToUpdate.setNome(faculdade.getNome());
            faculdadeToUpdate.setEndereco(faculdade.getEndereco());

            this.faculdadeRepository.save(faculdadeToUpdate);
            return MessageDTO.builder().message("Faculdade atualizada com sucesso").build();
        }
        return MessageDTO.builder().message("Id não encontrado").build();

    }


    @DeleteMapping("/{id}")
    public MessageDTO deleteFaculdade(@PathVariable Long id) {

        Faculdade faculdade = verifyIfExistsFaculdade(id);
        if(faculdade != null) {

            this.faculdadeRepository.delete(faculdade);
            return MessageDTO
                    .builder()
                    .message("Faculdade deletada com sucesso!")
                    .build();
        }
        this.faculdadeRepository.delete(faculdade);
        return MessageDTO
                .builder()
                .message("Faculdade não encontrada!")
                .build();
    }

    public Faculdade verifyIfExistsFaculdade(Long id) {
        Faculdade faculdadeSelecionada = this.faculdadeRepository.findById(id).get();
        if(faculdadeSelecionada != null) {
            return faculdadeSelecionada;
        }
        return null;
    }
}
