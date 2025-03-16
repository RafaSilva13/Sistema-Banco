package com.mycompany.models.ClassesAuxiliares;

import com.mycompany.models.Conta.Cliente;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


// Classe interna para o diálogo de validação de senha
public class DialogValidarSenha extends JDialog {
    private JPasswordField txtSenha;
    private boolean senhaValida;

    public DialogValidarSenha(JFrame parent, Cliente usuarioLogado) {
        super(parent, "Validar Senha", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Campo de senha
        painel.add(new JLabel("Digite sua senha:"));
        txtSenha = new JPasswordField();
        painel.add(txtSenha);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(e -> {
            String senhaDigitada = new String(txtSenha.getPassword());
            if (usuarioLogado.getSenha().equals(senhaDigitada)) {
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