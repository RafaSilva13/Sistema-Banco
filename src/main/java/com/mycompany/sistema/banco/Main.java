package com.mycompany.sistema.banco;

import com.mycompany.exceptions.*;
import com.mycompany.view.TelaLogin;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class Main {
    public static void main(String[] args) throws CpfException, EmailException, TelefoneException {
        new TelaLogin().exibirTelaLogin();
    }
}
