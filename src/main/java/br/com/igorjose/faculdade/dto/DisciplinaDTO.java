package br.com.igorjose.faculdade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {

    private Long codigo;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

    @NotEmpty
    private Integer cargaHoraria;
}
