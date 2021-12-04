package br.com.igorjose.faculdade.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FaculdadeNotFound extends Throwable{

    public FaculdadeNotFound() {
        super("Faculdade não encontrada!");
    }
}
