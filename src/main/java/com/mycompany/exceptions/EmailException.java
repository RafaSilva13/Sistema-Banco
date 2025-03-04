package com.mycompany.exceptions;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class EmailException extends Exception{

    public EmailException() {
        super("Email invalido");
    }

    public EmailException(String message) {
        super(message);
    }
}
