package com.mycompany.view;

import com.mycompany.models.Conta.Gerente;
import com.mycompany.models.Conta.RendaFixa;
import com.mycompany.models.Conta.RendaVariavel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Tela Gerente
public class TelaGerente {
    
    private JFrame tela;
    private JPanel principal;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Gerente usuarioLogado;

    public void exibirTelaGerentes(Gerente gerente) {
        this.usuarioLogado = gerente;
                
        // Cria uma nova janela
        tela = new JFrame("Área Gerente");
        
        // Define o fechamento do programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        principal = new JPanel();
        principal.setLayout(new BorderLayout());
                
        // Adiciona o JTabbedPane à janela
        principal.add(exibeTelaGerente());
        
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
    
    public JTabbedPane exibeTelaGerente() {
        // Cria um JTabbedPane para organizar as abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona as abas com as funcionalidades
        abas.addTab("Movimentações Financeiras", criarPainelMovimentacoes());
        abas.addTab("Cadastrar Renda Fixa", criarPainelRendaFixa());
        abas.addTab("Cadastrar Renda Variável", criarPainelRendaVariavel());
        abas.addTab("Avaliar Crédito", criarPainelAvaliacaoCredito());

        return abas;
    }

    // Painel de Movimentações Financeiras
    private JPanel criarPainelMovimentacoes() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

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

                // Lógica para confirmar a movimentação financeira
                if (valor > 1000000) { // Verifica se o valor é acima de 1 milhão
                    JOptionPane.showMessageDialog(null, "Movimentação financeira acima de 1 milhão realizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Movimentação financeira realizada com sucesso!");
                }
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

    // Painel de Cadastro de Renda Fixa
    private JPanel criarPainelRendaFixa() {
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("Descrição:"));
        JTextField txtDescricao = new JTextField();
        painel.add(txtDescricao);

        painel.add(new JLabel("Taxa de Rendimento (%):"));
        JTextField txtTaxaRendimento = new JTextField();
        painel.add(txtTaxaRendimento);

        painel.add(new JLabel("Prazo Mínimo (meses):"));
        JTextField txtPrazoMinimo = new JTextField();
        painel.add(txtPrazoMinimo);

        painel.add(new JLabel("Prazo Máximo (meses):"));
        JTextField txtPrazoMaximo = new JTextField();
        painel.add(txtPrazoMaximo);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = txtDescricao.getText();
                double taxaRendimento = Double.parseDouble(txtTaxaRendimento.getText());
                int prazoMinimo = Integer.parseInt(txtPrazoMinimo.getText());
                int prazoMaximo = Integer.parseInt(txtPrazoMaximo.getText());

                // Lógica para cadastrar a renda fixa
                RendaFixa rendaFixa = new RendaFixa(descricao, taxaRendimento, prazoMinimo, prazoMaximo);
                usuarioLogado.cadastrarRendaFixa(rendaFixa);
                JOptionPane.showMessageDialog(null, "Renda fixa cadastrada com sucesso!");
            }
        });

        btnCancelar.addActionListener(e -> {
            txtDescricao.setText("");
            txtTaxaRendimento.setText("");
            txtPrazoMinimo.setText("");
            txtPrazoMaximo.setText("");
        });

        painel.add(btnCadastrar);
        painel.add(btnCancelar);

        return painel;
    }

    // Painel de Cadastro de Renda Variável
    private JPanel criarPainelRendaVariavel() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("Descrição:"));
        JTextField txtDescricao = new JTextField();
        painel.add(txtDescricao);

        painel.add(new JLabel("Percentual de Risco (%):"));
        JTextField txtPercentualRisco = new JTextField();
        painel.add(txtPercentualRisco);

        painel.add(new JLabel("Rentabilidade Esperada (%):"));
        JTextField txtRentabilidadeEsperada = new JTextField();
        painel.add(txtRentabilidadeEsperada);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = txtDescricao.getText();
                double percentualRisco = Double.parseDouble(txtPercentualRisco.getText());
                double rentabilidadeEsperada = Double.parseDouble(txtRentabilidadeEsperada.getText());

                // Lógica para cadastrar a renda variável
                RendaVariavel rendaVariavel = new RendaVariavel(descricao, percentualRisco, rentabilidadeEsperada);
                usuarioLogado.cadastrarRendaVariavel(rendaVariavel);
                JOptionPane.showMessageDialog(null, "Renda variável cadastrada com sucesso!");
            }
        });

        btnCancelar.addActionListener(e -> {
            txtDescricao.setText("");
            txtPercentualRisco.setText("");
            txtRentabilidadeEsperada.setText("");
        });

        painel.add(btnCadastrar);
        painel.add(btnCancelar);

        return painel;
    }

    // Painel de Avaliação de Crédito
    private JPanel criarPainelAvaliacaoCredito() {
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10)); // Aumentamos para 5 linhas para incluir o prazo

        // Campos de entrada
        painel.add(new JLabel("CPF do Cliente:"));
        JTextField txtCpfCliente = new JTextField();
        painel.add(txtCpfCliente);

        painel.add(new JLabel("Valor do Crédito:"));
        JTextField txtValorCredito = new JTextField();
        painel.add(txtValorCredito);

        painel.add(new JLabel("Prazo (meses):")); // Novo campo para o prazo
        JTextField txtPrazo = new JTextField();
        painel.add(txtPrazo);

        painel.add(new JLabel("Aprovar? (Sim/Não):"));
        JComboBox<String> cbAprovacao = new JComboBox<>(new String[]{"Sim", "Não"});
        painel.add(cbAprovacao);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Captura os valores dos campos
                    String cpfCliente = txtCpfCliente.getText();
                    double valorCredito = Double.parseDouble(txtValorCredito.getText());
                    int prazo = Integer.parseInt(txtPrazo.getText()); // Captura o prazo
                    boolean aprovado = cbAprovacao.getSelectedItem().equals("Sim");

                    // Lógica para avaliar o crédito
                    usuarioLogado.avaliarCredito(cpfCliente, valorCredito, prazo, aprovado);
                    JOptionPane.showMessageDialog(null, "Avaliação de crédito realizada com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor ou prazo inválido. Insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            // Limpa os campos ao cancelar
            txtCpfCliente.setText("");
            txtValorCredito.setText("");
            txtPrazo.setText("");
            cbAprovacao.setSelectedIndex(0);
        });

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        return painel;
    }
}