package com.mycompany.models.ClassesAuxiliares;

import com.mycompany.models.Conta.Cliente;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// Classe interna para o diálogo de validação de senha
public class DialogValidarSenha extends JDialog {
    private JTextField txtSenha;
    private boolean senhaValida;

    public DialogValidarSenha(JFrame parent, Cliente usuarioLogado) {
        super(parent, "Validar Senha", true);
        setSize(300, 180);
        setLocationRelativeTo(parent);

        JPanel painel = new JPanel(new GridLayout(2, 1, 10, 10));
        painel.setBorder(new EmptyBorder(10, 10, 0, 10));        

        // Campo de senha
        JPanel areaSenha = new JPanel();

        areaSenha.setBorder(BorderFactory.createTitledBorder("Digite sua senha:"));
        txtSenha = new JTextField(15);
        areaSenha.add(txtSenha);

        painel.add(areaSenha);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(e -> {      
            String textoSenha = txtSenha.getText();
            
            if (usuarioLogado.validaSenha(textoSenha)) {
                senhaValida = true;
                dispose(); // Fecha o diálogo
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            senhaValida = false;
            dispose(); // Fecha o diálogo
        });

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        painel.add(painelBotoes);
        add(painel);
    }

    // Método para verificar se a senha foi validada
    public boolean isSenhaValida() {
        return senhaValida;
    }
}