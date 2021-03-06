package br.com.igorjose.faculdade.service;

import java.util.List;

import br.com.igorjose.faculdade.dto.DisciplinaDTO;
import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.DisciplinaNotFound;
import br.com.igorjose.faculdade.models.Disciplina;
import br.com.igorjose.faculdade.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listAll() {
        List<Disciplina> disciplinaList = this.disciplinaRepository.findAll();
        return disciplinaList;
    }

    public MessageDTO createDisciplina(DisciplinaDTO disciplinaDTO) {

        Disciplina disciplinaToSaved = Disciplina
                .builder()
                .nome(disciplinaDTO.getNome())
                .cargaHoraria(disciplinaDTO.getCargaHoraria())
                .build();
        this.disciplinaRepository.save(disciplinaToSaved);

        return MessageDTO
                .builder()
                .message("Disciplina Cadastrada com sucesso!")
                .build();
    }
    public Disciplina findDisciplinaById(Long id) throws DisciplinaNotFound {
        Disciplina disciplina = verifyIfExistsDisciplina(id);
        return disciplina;
    }

    public MessageDTO updateDisciplina(Long id, DisciplinaDTO disciplinaDTO) throws DisciplinaNotFound {

        Disciplina disciplinaToUpdate = verifyIfExistsDisciplina(id);

        if(disciplinaToUpdate != null) {
            disciplinaToUpdate.setNome(disciplinaDTO.getNome());
            disciplinaToUpdate.setCargaHoraria(disciplinaDTO.getCargaHoraria());
            this.disciplinaRepository.save(disciplinaToUpdate);
            return MessageDTO.builder().message("Disciplina atualizada com sucesso").build();
        }
        return MessageDTO.builder().message("Disciplina n??o encontrada").build();
    }

    public MessageDTO deleteDisciplina(Long id) throws DisciplinaNotFound {

        Disciplina disciplina = verifyIfExistsDisciplina(id);

        if(disciplina != null) {
            this.disciplinaRepository.delete(disciplina);
            return MessageDTO.builder().message("Disciplina excluida com sucesso").build();
        }
        return MessageDTO.builder().message("Disciplina n??o encontrada").build();
    }

    public Disciplina verifyIfExistsDisciplina(Long id) throws DisciplinaNotFound {

        return this.disciplinaRepository
                .findById(id)
                .orElseThrow(() -> new DisciplinaNotFound(id));
    }
}
