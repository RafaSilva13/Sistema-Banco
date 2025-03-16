package com.mycompany.view;

import com.mycompany.eventos.*;
import com.mycompany.exceptions.*;
import com.mycompany.models.ClassesAuxiliares.*;
import com.mycompany.models.Conta.Cliente;
import com.mycompany.models.Conta.RendaFixa;
import com.mycompany.models.Conta.RendaVariavel;
import com.mycompany.models.Conta.Transacao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Tela Cliente
public class TelaCliente {
    
    private JFrame tela;
    private JPanel principal;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private Cliente usuarioLogado;
    
    private JList<Cliente> jlCliente;
    private JList<RendaFixa> jlRendasFixas;
    private JList<RendaVariavel> jlRendasVariaveis;

    public void exibirTelaClientes(Cliente cliente) {
        
        this.usuarioLogado = cliente;
                
        // Cria uma nova janela
        tela = new JFrame("Area Cliente");
        
        DefaultListModel<Cliente> modelClientes = new DefaultListModel<>();
        jlCliente = new JList<>(modelClientes);
        
        DefaultListModel<RendaFixa> modelRendaFixa = new DefaultListModel<>();
        jlRendasFixas = new JList<>(modelRendaFixa);
        
        DefaultListModel<RendaVariavel> modelRendaVariavel = new DefaultListModel<>();
        jlRendasVariaveis = new JList<>(modelRendaVariavel);
        
        tela.addWindowListener(new GerenciaClientesTelaClientes(this));
        tela.addWindowListener(new GerenciaRendasFixasTelaClientes(this));
        tela.addWindowListener(new GerenciaRendasVariaveisTelaClientes(this));

        // Define o excerramento do programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adiciona o menu de Opções
//        tela.setJMenuBar(criaBarraDeOpcoes());

        principal = new JPanel();
        principal.setLayout(new BorderLayout());
                
        // Adiciona o JTabbedPane à janela        tela.addWindowListener(new GerenciaRendasVariaveisTelaClientes(this));

        principal.add(exibeTelaCliente());
        
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
    
    public JTabbedPane exibeTelaCliente() {
        // Cria um JTabbedPane para organizar as abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona as abas com as funcionalidades
        abas.addTab("Transferência", criarPainelTransferencia());
        abas.addTab("Saldo/Extrato", criarPainelExtrato());
        abas.addTab("Investir em Renda Fixa", criarPainelInvestimentoRendaFixa());
        abas.addTab("Investir em Renda Variável", criarPainelInvestimentoRendaVariavel());
        abas.addTab("Solicitar Crédito", criarPainelSolicitarCredito());

        return abas;
    }

    // Painel de Transferência
    private JPanel criarPainelTransferencia() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("Conta Destino:"));
        JTextField txtContaDestino = new JTextField();
        painel.add(txtContaDestino);

        painel.add(new JLabel("Valor:"));
        JTextField txtValor = new JTextField();
        painel.add(txtValor);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String contaDestino = txtContaDestino.getText();
                    double valor = Double.parseDouble(txtValor.getText());

                    // Exibe o diálogo de validação de senha
                    DialogValidarSenha dialog = new DialogValidarSenha(tela, usuarioLogado);
                    dialog.setVisible(true);

                    // Verifica se a senha foi validada
                    if (dialog.isSenhaValida()) {
                        // Realiza a transferência
                                                
                        if (usuarioLogado.transferir(buscaConta(contaDestino), valor)) {
                            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha na transferência. Verifique os dados e saldo.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada. Senha não validada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            txtContaDestino.setText("");
            txtValor.setText("");
        });

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        return painel;
    }

    // Painel de Consulta de Extrato
    private JPanel criarPainelExtrato() {
        JPanel painel = new JPanel(new BorderLayout());

        // Exibição do saldo
        JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getSaldo());
        painel.add(lblSaldo, BorderLayout.NORTH);

        // Exibição do extrato
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Data");
        model.addColumn("Descrição");
        model.addColumn("Valor");

        for (Transacao transacao : usuarioLogado.getExtrato()) {
            model.addRow(new Object[]{transacao.getDataHora(), transacao.getDescricao(), transacao.getValor()});
        }

        JTable tabelaExtrato = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelaExtrato);
        painel.add(scrollPane, BorderLayout.CENTER);

        return painel;
    }

    // Painel de Investimento em Renda Fixa
    private JPanel criarPainelInvestimentoRendaFixa() {
        JPanel painel = new JPanel(new BorderLayout());

        // Lista de investimentos em renda fixa
        DefaultListModel<String> model = new DefaultListModel<>();
        for (RendaFixa rendaFixa : listaRendasFixas()) {
            model.addElement(rendaFixa.toString());
        }

        JScrollPane scrollPane = new JScrollPane(jlRendasFixas);
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel para o campo de valor e botões
        JPanel painelInferior = new JPanel(new BorderLayout());

        // Campo para valor do investimento        
        JPanel painelValor = new JPanel(new GridLayout(1, 1, 10, 10));
        painelValor.setBorder(BorderFactory.createTitledBorder("Valor do Investimento:"));
        JTextField textoValor = new JTextField(15);
        
        painelValor.add(textoValor);

        // Botões
        JButton btnInvestir = new JButton("Investir");
        JButton btnCancelar = new JButton("Cancelar");

        btnInvestir.addActionListener(e -> {
            // Captura o valor do campo de texto no momento do clique
            String valorInvestimento = textoValor.getText();

            new BotaoCriarInvestimentoRendaFixaTelaCliente(
                    jlRendasFixas.getSelectedIndex(), 
                    valorInvestimento, 
                    usuarioLogado, 
                    this, 
                    tela).actionPerformed(e);
        });
            
        btnCancelar.addActionListener(e -> textoValor.setText(""));

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnInvestir);
        painelBotoes.add(btnCancelar);

        // Adiciona o campo de valor e os botões ao painel inferior
        painelInferior.add(painelValor, BorderLayout.CENTER);
        painelInferior.add(painelBotoes, BorderLayout.SOUTH);

        // Adiciona o painel inferior ao painel principal
        painel.add(painelInferior, BorderLayout.SOUTH);

        return painel;
    }
    
    // Painel de Investimento em Renda Variável
    private JPanel criarPainelInvestimentoRendaVariavel() {
        JPanel painel = new JPanel(new BorderLayout());

        // Lista de investimentos em renda variável
        DefaultListModel<String> model = new DefaultListModel<>();
        for (RendaVariavel rendaVariavel : listaRendasVariaveis()) {
            model.addElement(rendaVariavel.toString());
        }

        JList<String> listaInvestimentos = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(listaInvestimentos);
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel para o campo de valor e botões
        JPanel painelInferior = new JPanel(new BorderLayout());

        // Campo para valor do investimento        
        JPanel painelValor = new JPanel(new GridLayout(1, 1, 10, 10));
        painelValor.setBorder(BorderFactory.createTitledBorder("Valor do Investimento:"));
        JTextField textoValor = new JTextField(15);
        
        painelValor.add(textoValor);
        
        // Botões
        JButton btnInvestir = new JButton("Investir");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnInvestir.addActionListener(e -> {
                // Captura o valor do campo de texto no momento do clique
                String valorInvestimento = textoValor.getText();

                // Chama o listener do botão com o valor atual do campo de texto
                new BotaoCriarInvestimentoRendaVariavelTelaCliente(
                    listaInvestimentos.getSelectedIndex(), 
                    valorInvestimento, 
                    usuarioLogado, 
                    this, 
                    tela
                ).actionPerformed(e);
            });

        btnCancelar.addActionListener(e -> textoValor.setText(""));

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnInvestir);
        painelBotoes.add(btnCancelar);

        // Adiciona o campo de valor e os botões ao painel inferior
        painelInferior.add(painelValor, BorderLayout.CENTER);
        painelInferior.add(painelBotoes, BorderLayout.SOUTH);

        // Adiciona o painel inferior ao painel principal
        painel.add(painelInferior, BorderLayout.SOUTH);

        return painel;
    }

    // Painel de Solicitação de Crédito
    private JPanel criarPainelSolicitarCredito() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        painel.add(new JLabel("Valor Solicitado:"));
        JTextField txtValor = new JTextField();
        painel.add(txtValor);

        painel.add(new JLabel("Prazo (meses):"));
        JTextField txtPrazo = new JTextField();
        painel.add(txtPrazo);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Captura os valores dos campos
                    double valor = Double.parseDouble(txtValor.getText());
                    int prazo = Integer.parseInt(txtPrazo.getText());

                    // Exibe o diálogo de validação de senha
                    DialogValidarSenha dialog = new DialogValidarSenha(tela, usuarioLogado);
                    dialog.setVisible(true);

                    // Verifica se a senha foi validada
                    if (dialog.isSenhaValida()) {
                        // Solicita o crédito
                        usuarioLogado.solicitarCredito(valor, prazo);
                        JOptionPane.showMessageDialog(null, "Solicitação de crédito realizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada. Senha não validada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor ou prazo inválido. Insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            // Limpa os campos ao cancelar
            txtValor.setText("");
            txtPrazo.setText("");
        });

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        return painel;
    }
    
    public List<Cliente> listaClientes() {
        DefaultListModel<Cliente> modelClientes = (DefaultListModel<Cliente>) jlCliente.getModel();
        List<Cliente> clientes = new ArrayList<>();

        for (int i = 0; i < modelClientes.size(); i++) {
            clientes.add(modelClientes.get(i));
        }

        return clientes;
    }

    public void carregaClientes(List<Cliente> clientes) {
        DefaultListModel<Cliente> modelCliente = (DefaultListModel<Cliente>) jlCliente.getModel();
        modelCliente.clear(); // Limpa a lista antes de adicionar os novos registros

        for (Cliente c : clientes) {
            modelCliente.addElement(c);
        }
    }
    
    public List<RendaFixa> listaRendasFixas() {
        DefaultListModel<RendaFixa> modelRendasFixas = (DefaultListModel<RendaFixa>) jlRendasFixas.getModel();
        List<RendaFixa> rendasFixas = new ArrayList<>();

        for (int i = 0; i < modelRendasFixas.size(); i++) {
            rendasFixas.add(modelRendasFixas.get(i));
        }

        return rendasFixas;
    }

    public void carregaRendasFixas(List<RendaFixa> rendasFixas) {
        DefaultListModel<RendaFixa> modelRendasFixas = (DefaultListModel<RendaFixa>) jlRendasFixas.getModel();
        modelRendasFixas.clear(); // Limpa a lista antes de adicionar os novos registros

        for (RendaFixa rf : rendasFixas) {
            modelRendasFixas.addElement(rf);
        }
    }
    
    public void carregaRendasVariaveis(List<RendaVariavel> rendas) {
        DefaultListModel<RendaVariavel> modelRendaVariavel = (DefaultListModel<RendaVariavel>) jlRendasVariaveis.getModel();

        for (RendaVariavel r : rendas) {
            modelRendaVariavel.addElement(r);
        }
    }
    
    public List<RendaVariavel> listaRendasVariaveis() {
        DefaultListModel<RendaVariavel> modelRendaVariavel = (DefaultListModel<RendaVariavel>) jlRendasVariaveis.getModel();
        List<RendaVariavel> rendas = new ArrayList<>();

        for (int i = 0; i < modelRendaVariavel.size(); i++) {
            rendas.add(modelRendaVariavel.get(i));
        }

        return rendas;
    }
    
    private Cliente buscaConta(String numeroConta) {
        
        DefaultListModel<Cliente> modelClientes = (DefaultListModel<Cliente>) jlCliente.getModel();
       
        for (int i = 0; i < modelClientes.size(); i++) {            
            if(modelClientes.get(i).equals(numeroConta)) {
                return modelClientes.get(i);
            }
        }

        return null;
    }
}
