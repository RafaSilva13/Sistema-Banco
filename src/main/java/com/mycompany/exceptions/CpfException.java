package com.mycompany.exceptions;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class CpfException extends Exception {

    public CpfException() {
        super("CPF nao valido!");
    }

    public CpfException(String message) {
        super(message);
    }
    
}
