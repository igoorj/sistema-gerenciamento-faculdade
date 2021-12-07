package br.com.igorjose.faculdade.service;

import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.FaculdadeNotFound;
import br.com.igorjose.faculdade.models.Faculdade;
import br.com.igorjose.faculdade.repository.FaculdadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FaculdadeService {

    private FaculdadeRepository faculdadeRepository;

    @Autowired
    public FaculdadeService(FaculdadeRepository faculdadeRepository) {
        this.faculdadeRepository = faculdadeRepository;
    }

    public List<Faculdade> getAllFaculdades() {
        return this.faculdadeRepository.findAll();
    }

    public Faculdade getFaculdadeById(Long id) throws FaculdadeNotFound {
        Faculdade faculdade = verifyIfExistsFaculdade(id);
        return faculdade;
    }

    public MessageDTO createFaculdade(Faculdade faculdade) {
        this.faculdadeRepository.save(faculdade);
        return MessageDTO.builder().message("Faculdade cadastrada com sucesso").build();
    }

    public MessageDTO updateFaculdade(Long id, Faculdade faculdade) throws FaculdadeNotFound {

        Faculdade faculdadeToUpdate = verifyIfExistsFaculdade(id);

        faculdadeToUpdate.setNome(faculdade.getNome());
        faculdadeToUpdate.setCursos(faculdade.getCursos());
        faculdadeToUpdate.setCnpj(faculdade.getCnpj());
        faculdadeToUpdate.setEndereco(faculdade.getEndereco());
        this.faculdadeRepository.save(faculdadeToUpdate);

        return MessageDTO.builder().message("Faculdade atualizada com sucesso!").build();
    }

    public MessageDTO deleteFaculdade(Long id) throws FaculdadeNotFound{
        Faculdade faculdadeToDelete = verifyIfExistsFaculdade(id);
        this.faculdadeRepository.delete(faculdadeToDelete);
        return MessageDTO.builder().message("Faculdade deletada com sucesso!").build();
    }

    public Faculdade verifyIfExistsFaculdade(Long id) throws FaculdadeNotFound {
        return this.faculdadeRepository
                .findById(id)
                .orElseThrow(() -> new FaculdadeNotFound());
    }
}
