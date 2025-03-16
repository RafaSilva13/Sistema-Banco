package com.mycompany.exceptions;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class SaldoInsuficienteException extends IllegalArgumentException  {

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
