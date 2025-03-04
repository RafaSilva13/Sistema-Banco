package com.mycompany.exceptions;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class TelefoneException extends Exception {
    public TelefoneException() {
        super("Formato de telefone incorreto.");
    }

    public TelefoneException(String message) {
        super(message);
    }
}
