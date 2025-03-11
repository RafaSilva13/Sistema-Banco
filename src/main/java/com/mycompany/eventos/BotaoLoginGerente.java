package com.mycompany.eventos;

import com.mycompany.exceptions.CpfException;
import com.mycompany.models.Conta.Gerente;
import com.mycompany.view.TelaGerente;
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

public class BotaoLoginGerente implements ActionListener {
    
    private final JFrame telaAtual;
    private final TelaLogin telaLogin;
    private final TelaGerente tela;
    private boolean verificado = false;
    private Gerente gerenteLogado;
    
    public BotaoLoginGerente(JFrame telaAtual, TelaGerente tela, TelaLogin telaLogin) {
        this.telaAtual = telaAtual;
        this.tela = tela;
        this.telaLogin = telaLogin;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String usuario = telaLogin.getCpfGerente();
        String senha = telaLogin.getSenhaGerente();
        List<Gerente> gerentes = telaLogin.listaGerentes();
        
        try {
            if (gerentes.size() > 0) {
                if (usuario.length() != 0 && senha.length() != 0) {
                    for (Gerente gerente : gerentes) {
                        if (gerente.autenticar(usuario, senha)) {
                            this.verificado = true;
                            this.gerenteLogado = gerente;
                        }
                    } 

                    if (this.verificado) {
                        telaAtual.dispose();
                        tela.exibirTelaGerentes(this.gerenteLogado);
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