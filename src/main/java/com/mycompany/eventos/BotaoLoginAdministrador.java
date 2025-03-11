package com.mycompany.eventos;

import com.mycompany.models.Conta.Administrador;
import com.mycompany.view.TelaAdministrador;
import com.mycompany.view.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class BotaoLoginAdministrador implements ActionListener {
    
    private final JFrame telaAtual;
    private final TelaAdministrador tela;
    private final TelaLogin telaLogin;
    
    public BotaoLoginAdministrador(JFrame telaAtual, TelaAdministrador tela, TelaLogin telaLogin) {
        this.telaAtual = telaAtual;
        this.tela = tela;
        this.telaLogin = telaLogin;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String usuario = telaLogin.getUsuarioAdministrador();
        String senha = telaLogin.getSenhaAdministrador();
        
        Administrador administrador = new Administrador();
        
        if (usuario.length() != 0 && senha.length() != 0) {
            if (administrador.fazLogin(usuario, senha)) {
                telaAtual.dispose();
                tela.exibirTelaAdministrador();
            } else {
                JOptionPane.showMessageDialog(telaAtual, "Senha ou usuário incorreto!");
            }
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Preencha todos os campos!");
        }
    }
}