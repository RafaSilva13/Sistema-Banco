package com.mycompany.sistema.banco;

import com.mycompany.exceptions.*;
import com.mycompany.views.TelaLogin;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class Main {
    public static void main(String[] args) throws CpfException, EmailException, TelefoneException {
        TelaLogin sistema = new TelaLogin();
        sistema.exibirTelaLogin();
    }
}
