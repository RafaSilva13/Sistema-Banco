package com.mycompany.eventos;

import com.mycompany.exceptions.CpfException;
import com.mycompany.models.Conta.Caixa;
import com.mycompany.view.TelaCaixa;
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

public class BotaoLoginCaixa implements ActionListener {
    
    private final JFrame telaAtual;
    private final TelaLogin telaLogin;
    private final TelaCaixa tela;
    private boolean verificado = false;
    private Caixa caixaLogado;
    
    public BotaoLoginCaixa(JFrame telaAtual, TelaCaixa tela, TelaLogin telaLogin) {
        this.telaAtual = telaAtual;
        this.tela = tela;
        this.telaLogin = telaLogin;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String usuario = telaLogin.getCpfCaixa();
        String senha = telaLogin.getSenhaCaixa();
        List<Caixa> caixas = telaLogin.listaCaixas();
        
        try {
            if (caixas.size() > 0) {
                if (usuario.length() != 0 && senha.length() != 0) {
                    for (Caixa caixa : caixas) {
                        if (caixa.autenticar(usuario, senha)) {
                            this.verificado = true;
                            this.caixaLogado = caixa;
                        }
                    } 

                    if (this.verificado) {
                        telaAtual.dispose();
                        tela.exibirTelaCaixas(this.caixaLogado);
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