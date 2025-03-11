package com.mycompany.view;

import com.mycompany.models.Conta.Cliente;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Tela Cliente
public class TelaCliente {
    
    private Cliente usuarioLogado;

    public void exibirTelaClientes(Cliente cliente) {
        this.usuarioLogado = cliente;
    }
}
