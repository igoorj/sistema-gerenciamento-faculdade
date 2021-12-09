package br.com.igorjose.faculdade.dto;

import br.com.igorjose.faculdade.models.Aluno;
import br.com.igorjose.faculdade.models.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    private Long codigo;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String nome;

    @Valid
    @NotEmpty
    private List<Disciplina> disciplinaList;

    private List<Aluno> alunosMatriculados;
}
