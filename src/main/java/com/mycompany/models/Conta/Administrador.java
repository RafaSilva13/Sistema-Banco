package com.mycompany.models.Conta;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class Administrador {
    
    private String USUARIO_PADRAO = "admin";
    private String SENHA_PADRAO = "123";
    
    public boolean fazLogin(String usuario, String senha) {
        return this.USUARIO_PADRAO.equals(usuario) && this.SENHA_PADRAO.equals(senha);
    }
}
