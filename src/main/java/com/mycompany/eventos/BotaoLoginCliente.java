package com.mycompany.eventos;

import com.mycompany.exceptions.CpfException;
import com.mycompany.models.Conta.Cliente;
import com.mycompany.view.TelaCliente;
import java.awt.event.ActionListener;
import com.mycompany.view.TelaLogin;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class BotaoLoginCliente implements ActionListener {
    
    private final JFrame telaAtual;
    private final TelaLogin telaLogin;
    private final TelaCliente tela;
    private boolean verificado = false;
    private Cliente clienteLogado;
    
    public BotaoLoginCliente(JFrame telaAtual, TelaCliente tela, TelaLogin telaLogin) {
        this.telaAtual = telaAtual;
        this.tela = tela;
        this.telaLogin = telaLogin;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String usuario = telaLogin.getCpfCliente();
        String senha = telaLogin.getSenhaCliente();
        List<Cliente> clientes = telaLogin.listaClientes();
        
        try {
            if (clientes.size() > 0) {
                if (usuario.length() != 0 && senha.length() != 0) {
                    for (Cliente cliente : clientes) {
                        if (cliente.autenticar(usuario, senha)) {
                            this.verificado = true;
                            this.clienteLogado = cliente;
                        }
                    } 

                    if (this.verificado) {
                        telaAtual.dispose();
                        tela.exibirTelaClientes(this.clienteLogado);
                    } else {
                        JOptionPane.showMessageDialog(telaAtual, "Senha ou usuário incorreto!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(telaAtual, "Preencha todos os campos!");
                }
            }
            else {
                JOptionPane.showMessageDialog(telaAtual, "Nenhum usuário cadastrado!");
            }
        } catch (CpfException ex) {
            JOptionPane.showMessageDialog(telaAtual, ex.getMessage());
        }
    }
}