package br.com.igorjose.faculdade.dto;

import br.com.igorjose.faculdade.models.Curso;
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
public class FaculdadeDTO {

    private Long id;

    @NotEmpty
    @Size(min = 8, max = 12)
    private String cnpj;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String nome;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String endereco;

    @Valid
    @NotEmpty
    private List<Curso> cursos;
}
