package br.com.igorjose.faculdade.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DisciplinaNotFound extends Throwable{

    public DisciplinaNotFound(Long id) {
        super("Discplina de id: " +id+ " não encontrada!");
    }
}
