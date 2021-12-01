package br.com.igorjose.faculdade.exceptions;

public class DisciplinaNotFound extends Throwable{

    public DisciplinaNotFound(Long id) {
        super("Discplina de id: " +id+ " não encontrada!");
    }
}
