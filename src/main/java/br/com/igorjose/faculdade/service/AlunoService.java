package br.com.igorjose.faculdade.service;

import br.com.igorjose.faculdade.dto.AlunoDTO;
import br.com.igorjose.faculdade.dto.MessageDTO;
import br.com.igorjose.faculdade.exceptions.AlunoNotFound;
import br.com.igorjose.faculdade.models.Aluno;
import br.com.igorjose.faculdade.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno>  getAllAlunos() {
        return this.alunoRepository.findAll();
    }

    public Aluno findAlunoById(Long id) throws AlunoNotFound {
        Aluno aluno = verifyIsExistsAluno(id);
        return aluno;
    }

    public MessageDTO createAluno(AlunoDTO alunoDTO) {

        Aluno alunoToSaved = Aluno
                .builder()
                .nome(alunoDTO.getNome())
                .cpf(alunoDTO.getCpf())
                .dataNascimento(alunoDTO.getDataNascimento())
                .build();
        this.alunoRepository.save(alunoToSaved);
        return MessageDTO.builder().message("Aluno cadastrado com sucesso").build();
    }

    public MessageDTO updateAluno(Long id, AlunoDTO alunoDTO) throws AlunoNotFound {
        Aluno alunoToUpdate = verifyIsExistsAluno(id);
        if(alunoToUpdate != null) {
            alunoToUpdate.setNome(alunoDTO.getNome());
            alunoToUpdate.setCpf(alunoDTO.getCpf());
            alunoToUpdate.setDataNascimento(alunoDTO.getDataNascimento());

            this.alunoRepository.save(alunoToUpdate);
            return MessageDTO.builder().message("Aluno atualizado com sucesso!").build();
        }
        return MessageDTO.builder().message("Aluno não encontrado").build();
    }

    public MessageDTO deleteAluno(Long id) throws AlunoNotFound {
        Aluno alunoToDelete = verifyIsExistsAluno(id);
        this.alunoRepository.delete(alunoToDelete);
        return MessageDTO.builder().message("Aluno deletado com sucesso!").build();
    }

    public Aluno verifyIsExistsAluno(Long id) throws AlunoNotFound {

        return this.alunoRepository
                .findById(id)
                .orElseThrow(() -> new AlunoNotFound());
    }
}
