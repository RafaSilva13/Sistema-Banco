package com.mycompany.view;

import com.mycompany.models.Conta.Caixa;
import com.mycompany.models.Conta.Cliente;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Tela Caixa
public class TelaCaixa {
    
    private JFrame tela;
    private JPanel principal;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private Caixa usuarioLogado;

    public void exibirTelaCaixas(Caixa caixa) {
        this.usuarioLogado = caixa;
                
        // Cria uma nova janela
        tela = new JFrame("Área Caixa");
        
        // Define o fechamento do programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        principal = new JPanel();
        principal.setLayout(new BorderLayout());
                
        // Adiciona o JTabbedPane à janela
        principal.add(exibeTelaCaixa());
        
        // Adiciona o painel geral a janela
        tela.add(principal);
        
        // Define o tamanho do painel geral e sua posição como central
        tela.setSize(WIDTH, HEIGHT);
        tela.setLocationRelativeTo(null);
                
        // Bloqueia o redimensionamento da janela
        tela.setResizable(false);
        
        // Deixa o painel visível
        tela.setVisible(true);
        
        principal.revalidate();
        principal.repaint();
    }
    
    public JTabbedPane exibeTelaCaixa() {
        // Cria um JTabbedPane para organizar as abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona as abas com as funcionalidades
        abas.addTab("Saque", criarPainelSaque());
        abas.addTab("Depósito", criarPainelDeposito());
        abas.addTab("Transferência", criarPainelTransferencia());

        return abas;
    }

    // Painel de Saque
    private JPanel criarPainelSaque() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("CPF do Cliente:"));
        JTextField txtCpfCliente = new JTextField();
        painel.add(txtCpfCliente);

        painel.add(new JLabel("Valor:"));
        JTextField txtValor = new JTextField();
        painel.add(txtValor);

        painel.add(new JLabel("Senha:"));
        JPasswordField txtSenha = new JPasswordField();
        painel.add(txtSenha);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpfCliente = txtCpfCliente.getText();
                double valor = Double.parseDouble(txtValor.getText());
                String senha = new String(txtSenha.getPassword());

                // Lógica para confirmar o saque
                // Aqui você pode adicionar a lógica para encontrar o cliente pelo CPF e realizar o saque
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
            }
        });

        btnCancelar.addActionListener(e -> {
            txtCpfCliente.setText("");
            txtValor.setText("");
            txtSenha.setText("");
        });

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        return painel;
    }

    // Painel de Depósito
    private JPanel criarPainelDeposito() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("CPF do Cliente:"));
        JTextField txtCpfCliente = new JTextField();
        painel.add(txtCpfCliente);

        painel.add(new JLabel("Valor:"));
        JTextField txtValor = new JTextField();
        painel.add(txtValor);

        painel.add(new JLabel("Senha:"));
        JPasswordField txtSenha = new JPasswordField();
        painel.add(txtSenha);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpfCliente = txtCpfCliente.getText();
                double valor = Double.parseDouble(txtValor.getText());
                String senha = new String(txtSenha.getPassword());

                // Lógica para confirmar o depósito
                // Aqui você pode adicionar a lógica para encontrar o cliente pelo CPF e realizar o depósito
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
            }
        });

        btnCancelar.addActionListener(e -> {
            txtCpfCliente.setText("");
            txtValor.setText("");
            txtSenha.setText("");
        });

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        return painel;
    }

    // Painel de Transferência
    private JPanel criarPainelTransferencia() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("CPF do Cliente Origem:"));
        JTextField txtCpfClienteOrigem = new JTextField();
        painel.add(txtCpfClienteOrigem);

        painel.add(new JLabel("CPF do Cliente Destino:"));
        JTextField txtCpfClienteDestino = new JTextField();
        painel.add(txtCpfClienteDestino);

        painel.add(new JLabel("Valor:"));
        JTextField txtValor = new JTextField();
        painel.add(txtValor);

        painel.add(new JLabel("Senha:"));
        JPasswordField txtSenha = new JPasswordField();
        painel.add(txtSenha);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpfClienteOrigem = txtCpfClienteOrigem.getText();
                String cpfClienteDestino = txtCpfClienteDestino.getText();
                double valor = Double.parseDouble(txtValor.getText());
                String senha = new String(txtSenha.getPassword());

                // Lógica para confirmar a transferência
                // Aqui você pode adicionar a lógica para encontrar os clientes pelos CPFs e realizar a transferência
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
            }
        });

        btnCancelar.addActionListener(e -> {
            txtCpfClienteOrigem.setText("");
            txtCpfClienteDestino.setText("");
            txtValor.setText("");
            txtSenha.setText("");
        });

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        return painel;
    }
}