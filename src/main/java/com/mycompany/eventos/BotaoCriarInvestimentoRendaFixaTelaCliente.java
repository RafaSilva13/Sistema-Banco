package com.mycompany.eventos;

import com.mycompany.models.ClassesAuxiliares.DialogValidarSenha;
import com.mycompany.models.Conta.Cliente;
import com.mycompany.models.Conta.RendaFixa;
import com.mycompany.view.TelaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class BotaoCriarInvestimentoRendaFixaTelaCliente implements ActionListener {

    private int selectedIndex;
    private String txtValor;
    private TelaCliente telaCliente;
    private JFrame tela;  
    private Cliente usuarioLogado;
    
    public BotaoCriarInvestimentoRendaFixaTelaCliente(int selectedIndex, String txtValor, Cliente usuarioLogado, TelaCliente telaCliente, JFrame tela) {
        this.tela = tela;
        this.txtValor = txtValor;
        this.telaCliente = telaCliente;
        this.usuarioLogado = usuarioLogado;
        this.selectedIndex = selectedIndex;
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double valor = Double.parseDouble(txtValor);
            
            if (selectedIndex != -1) {
                RendaFixa rendaFixa = telaCliente.listaRendasFixas().get(selectedIndex);

                // Exibe o diálogo de validação de senha
                DialogValidarSenha dialog = new DialogValidarSenha(tela, usuarioLogado);
                dialog.setVisible(true);

                if (dialog.isSenhaValida()) {
                    if (usuarioLogado.investirRendaFixa(rendaFixa, valor)) {
                        JOptionPane.showMessageDialog(null, "Investimento em renda fixa realizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente para investir.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada. Senha não validada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um investimento.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
