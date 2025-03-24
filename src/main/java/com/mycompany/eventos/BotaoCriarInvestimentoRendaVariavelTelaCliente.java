package com.mycompany.eventos;

import com.mycompany.models.ClassesAuxiliares.DialogValidarSenha;
import com.mycompany.models.Conta.Cliente;
import com.mycompany.models.Conta.RendaFixa;
import com.mycompany.models.Conta.RendaVariavel;
import com.mycompany.view.TelaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class BotaoCriarInvestimentoRendaVariavelTelaCliente implements ActionListener {

    private int selectedIndex;
    private String valorTexto;
    private TelaCliente telaCliente;
    private JFrame tela;  
    private Cliente usuarioLogado;
    
    public BotaoCriarInvestimentoRendaVariavelTelaCliente(int selectedIndex, String valorTexto, Cliente usuarioLogado, TelaCliente telaCliente, JFrame tela) {
        this.tela = tela;
        this.valorTexto = valorTexto;
        this.telaCliente = telaCliente;
        this.usuarioLogado = usuarioLogado;
        this.selectedIndex = selectedIndex;
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double valor = Double.parseDouble(valorTexto);
            
            if (selectedIndex != -1 && !telaCliente.listaRendasVariaveis().isEmpty() && selectedIndex >= 0 && selectedIndex < telaCliente.listaRendasVariaveis().size()) {
                RendaVariavel rendaVariavel = telaCliente.listaRendasVariaveis().get(selectedIndex);

                // Exibe o diálogo de validação de senha
                DialogValidarSenha dialog = new DialogValidarSenha(tela, usuarioLogado);
                dialog.setVisible(true);

                if (dialog.isSenhaValida()) {
                    if (telaCliente.atualizaUsuarioLogadoInvestimentoRendaVariavel(rendaVariavel, valor)) {
                        JOptionPane.showMessageDialog(null, "Investimento em renda variável realizado com sucesso!");
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
        } finally {
            telaCliente.atualizarInterface(); // Atualiza a interface após a operação
        }
    }
}
