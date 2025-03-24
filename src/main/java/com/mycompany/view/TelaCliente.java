package com.mycompany.view;

import com.mycompany.models.ClassesAuxiliares.*;
import java.awt.event.ActionListener;
import com.mycompany.persistences.*;
import com.mycompany.models.Conta.*;
import com.mycompany.exceptions.*;
import java.awt.event.ActionEvent;
import com.mycompany.eventos.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;
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
    private JList<Investimento> jlMeusInvestimentos;

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
        
        DefaultListModel<Investimento> modelInvestimento = new DefaultListModel<>();
        jlMeusInvestimentos = new JList<>(modelInvestimento);
        
        // Carrega os dados iniciais
        carregaMeusInvestimentos(usuarioLogado.getInvestimentos());
        carregaClientes(new ClientePersistence().findAll());
        carregaRendasFixas(new RendaFixaPersistence().findAll());
        carregaRendasVariaveis(new RendaVariavelPersistence().findAll());
        
        tela.addWindowListener(new GerenciaClientesTelaClientes(this));
        tela.addWindowListener(new GerenciaRendasFixasTelaClientes(this));
        tela.addWindowListener(new GerenciaRendasVariaveisTelaClientes(this));
        
        // Define o excerramento do programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        principal = new JPanel();
        principal.setLayout(new BorderLayout());
        
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
        
        // Após carregar os dados
        tela.revalidate();
        tela.repaint();
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
        abas.addTab("Meus Investimentos", criarPainelMeusInvestimentos());
        abas.addTab("Editar Perfil", criarPainelEditarPerfil()); 

        return abas;
    }

    // Painel para exibir os investimentos feitos pelo cliente
    private JPanel criarPainelMeusInvestimentos() {
        JPanel painel = new JPanel(new BorderLayout());

        // Lista de investimentos feitos pelo cliente
        DefaultListModel<String> model = new DefaultListModel<>();
        
        for (Investimento investimento : usuarioLogado.getInvestimentos()) {
            model.addElement(investimento.toString());
        }

        JList<String> listaInvestimentos = new JList<>(model);
        
        JScrollPane scrollPane = new JScrollPane(listaInvestimentos);
        painel.add(scrollPane, BorderLayout.CENTER);

        // Botão para cancelar investimento
        JButton btnCancelarInvestimento = new JButton("Cancelar Investimento");
        
        btnCancelarInvestimento.addActionListener(e -> {
            int selectedIndex = listaInvestimentos.getSelectedIndex();
            
            if (selectedIndex != -1) {
                Investimento investimento = usuarioLogado.getInvestimentos().get(selectedIndex);
                usuarioLogado.getInvestimentos().remove(selectedIndex);
                
                model.remove(selectedIndex);
                
                JOptionPane.showMessageDialog(null, "Investimento cancelado com sucesso!");
                
                atualizarInterface();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um investimento para cancelar!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnCancelarInvestimento);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        return painel;
    }

    // Painel para editar os dados do cliente (exceto CPF e número da conta)
    private JPanel criarPainelEditarPerfil() {
        JPanel painel = new JPanel();
        
        // Campos de entrada
        JPanel painelCamposDeEntrada = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCamposDeEntrada.setBorder(new EmptyBorder(60, 0, 0, 0));        
        
        JPanel areaNome = new JPanel();
        areaNome.setBorder(BorderFactory.createTitledBorder("Nome:"));
        
        JTextField tfNome = new JTextField(15);
        tfNome.setText(usuarioLogado.getNome());
        areaNome.add(tfNome);
        
        JPanel areaCpf = new JPanel();
        areaCpf.setBorder(BorderFactory.createTitledBorder("Cpf:"));
        
        JTextField tfCpf = new JTextField(15);
        tfCpf.setText(usuarioLogado.getCpf().toString());
        tfCpf.setEditable(false);
        areaCpf.add(tfCpf);
        
        JPanel areaTelefone = new JPanel();
        areaTelefone.setBorder(BorderFactory.createTitledBorder("Telefone:"));
        
        JTextField tfTelefone = new JTextField(15);
        tfTelefone.setText(usuarioLogado.getNumeroDeTelefone().toString());
        areaTelefone.add(tfTelefone);
        
        JPanel areaEmail = new JPanel();
        areaEmail.setBorder(BorderFactory.createTitledBorder("Email:"));
        
        JTextField tfEmail = new JTextField(15);
        tfEmail.setText(usuarioLogado.getEmail().toString());
        areaEmail.add(tfEmail);
        
        JPanel areaSenha = new JPanel();
        areaSenha.setBorder(BorderFactory.createTitledBorder("Senha:"));
        
        JTextField tfSenha = new JTextField(15);
        areaSenha.add(tfSenha);
        
        painelCamposDeEntrada.add(areaNome);
        painelCamposDeEntrada.add(areaCpf);
        painelCamposDeEntrada.add(areaTelefone);
        painelCamposDeEntrada.add(areaEmail);
        painelCamposDeEntrada.add(areaSenha);
        
        painel.add(painelCamposDeEntrada);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            try {
                String nome = tfNome.getText();
                String telefone = tfTelefone.getText();
                String email = tfEmail.getText();
                String senha = tfSenha.getText();

                // Atualiza os dados do cliente
                usuarioLogado.setNome(nome);
                usuarioLogado.setNumeroDeTelefone(new Telefone(telefone));
                usuarioLogado.setEmail(new Email(email));
                usuarioLogado.setSenha(senha);

                // Salva as alterações no arquivo
                Persistence<Cliente> clientePersistence = new ClientePersistence();
                List<Cliente> clientes = clientePersistence.findAll();
                for (int i = 0; i < clientes.size(); i++) {
                    if (clientes.get(i).getCpf().equals(usuarioLogado.getCpf())) {
                        clientes.set(i, usuarioLogado);
                        break;
                    }
                }
                clientePersistence.save(clientes);

                JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
            } catch (EmailException | TelefoneException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            tfNome.setText(usuarioLogado.getNome());
            tfTelefone.setText(usuarioLogado.getNumeroDeTelefone().toString());
            tfEmail.setText(usuarioLogado.getEmail().toString());
            tfSenha.setText("");
        });

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        painel.add(painelBotoes);

        return painel;
    }
    
    // Painel de Transferência
    private JPanel criarPainelTransferencia() {
        JPanel painel = new JPanel();
        
        // Campos de entrada
        
        JPanel painelCamposEntrada = new JPanel(new GridLayout(1, 1, 10, 10));
        painelCamposEntrada.setBorder(new EmptyBorder(60, 0, 0, 0));        

        JPanel areaContaDestino = new JPanel();
        areaContaDestino.setBorder(BorderFactory.createTitledBorder("Conta Destino:")); 
        JTextField txtContaDestino = new JTextField(15);
        areaContaDestino.add(txtContaDestino);
        
        painelCamposEntrada.add(areaContaDestino);

        JPanel areaValor = new JPanel(new FlowLayout());
        areaValor.setBorder(BorderFactory.createTitledBorder("Valor:"));
        JTextField txtValor = new JTextField(15);
        areaValor.add(txtValor);
        
        painelCamposEntrada.add(areaValor);

        painel.add(painelCamposEntrada);
        
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
                } catch (CpfException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            txtContaDestino.setText("");
            txtValor.setText("");
        });

        JPanel painelBotoes = new JPanel(new FlowLayout());
        
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);
                
        painel.add(painelBotoes, BorderLayout.CENTER);
        
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
            model.addRow(new Object[]{ 
                transacao.getDataHora(), 
                transacao.getDescricao(), 
                transacao.getValor()
            });
        }

        JTable tabelaExtrato = new JTable(model);
        
        tabelaExtrato.getColumnModel().getColumn(0).setPreferredWidth(120); // Coluna "Data"
        tabelaExtrato.getColumnModel().getColumn(1).setPreferredWidth(380); // Coluna "Descrição"
        tabelaExtrato.getColumnModel().getColumn(2).setPreferredWidth(90); // Coluna "Valor
        
        tabelaExtrato.getColumnModel().getColumn(2).setCellRenderer(new CurrencyRenderer());

        JScrollPane scrollPane = new JScrollPane(tabelaExtrato);
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel para depósito e saque
        JPanel painelOperacoes = new JPanel(new GridLayout(2, 1, 10, 10));

        // Campo para valor do depósito
        JPanel areaDeposito = new JPanel();
        areaDeposito.setBorder(BorderFactory.createTitledBorder("Valor do Depósito:"));
        JTextField txtValorDeposito = new JTextField(12);
        areaDeposito.add(txtValorDeposito);
        
        painelOperacoes.add(areaDeposito);

        // Campo para valor do saque
        JPanel areaSaque = new JPanel();
        areaSaque.setBorder(BorderFactory.createTitledBorder("Valor do Saque:"));
        JTextField txtValorSaque = new JTextField(12);
        areaSaque.add(txtValorSaque);
        
        painelOperacoes.add(areaSaque);
        
        // Botão de depósito
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(txtValorDeposito.getText());
                
                usuarioLogado.depositar(valor);
                
                // Atualiza o saldo exibido
                lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getSaldo()); 
                
                // Adiciona ao extrato
                model.addRow( new Object[] { 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                    "Depósito", 
                    valor 
                }); 
                
                // Limpa o campo
                txtValorDeposito.setText("");
                
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JPanel areaBotaoDeposito = new JPanel();
        areaBotaoDeposito.add(btnDepositar);
        
        painelOperacoes.add(areaBotaoDeposito);

        // Botão de saque
        JButton btnSacar = new JButton("Sacar");
        
        btnSacar.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(txtValorSaque.getText());
                if (usuarioLogado.sacar(valor)) {
                    lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getSaldo()); // Atualiza o saldo exibido
                   
                    // Adiciona ao extrato
                    model.addRow(new Object[]{ 
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), 
                        "Saque", 
                        valor
                    }); 
                    
                    // Limpa o campo
                    txtValorSaque.setText(""); 
                    
                    JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException | SaldoInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JPanel areaBotaoSaque = new JPanel();
        areaBotaoSaque.add(btnSacar);
        
        painelOperacoes.add(areaBotaoSaque);

        // Adiciona o painel de operações ao painel principal
        painel.add(painelOperacoes, BorderLayout.SOUTH);

        return painel;
    }

    // Painel de Investimento em Renda Fixa
    public JPanel criarPainelInvestimentoRendaFixa() {
        JPanel painel = new JPanel(new BorderLayout());

        // Lista de investimentos em renda fixa disponíveis
        DefaultListModel<RendaFixa> model = new DefaultListModel<>();
        
        for (RendaFixa rendaFixa : getRendasFixasDisponiveis()) {
            model.addElement(rendaFixa);
        }

        JList<RendaFixa> listaInvestimentos = new JList<>(model);
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
            
            new BotaoCriarInvestimentoRendaFixaTelaCliente(
                listaInvestimentos.getSelectedIndex(), 
                valorInvestimento, 
                usuarioLogado, 
                this, 
                tela).actionPerformed(e);

                listaInvestimentos.clearSelection();
                atualizarInterface();
        });

        btnCancelar.addActionListener(e -> { 
            textoValor.setText("");
            atualizarInterface();
        });

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

        // Lista de investimentos em renda variável disponíveis
        DefaultListModel<RendaVariavel> model = new DefaultListModel<>();
        for (RendaVariavel rendaVariavel : getRendasVariaveisDisponiveis()) {
            model.addElement(rendaVariavel);
        }

        JList<RendaVariavel> listaInvestimentos = new JList<>(model);
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
            
            listaInvestimentos.clearSelection();
            atualizarInterface();
        });

        btnCancelar.addActionListener(e -> { 
            textoValor.setText("");
            atualizarInterface();
        });

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
        JPanel painel = new JPanel();
        
        // Campos de entrada
        
        JPanel painelCamposEntrada = new JPanel(new GridLayout(1, 2, 10, 10));
        painelCamposEntrada.setBorder(new EmptyBorder(60, 0, 0, 0));        

        JPanel areaValorSolicitado = new JPanel();
        areaValorSolicitado.setBorder(BorderFactory.createTitledBorder("Valor Solicitado:"));
        JTextField txtValor = new JTextField(15);
        areaValorSolicitado.add(txtValor);
                
        painelCamposEntrada.add(areaValorSolicitado);

        JPanel areaPrazo = new JPanel();
        areaPrazo.setBorder(BorderFactory.createTitledBorder("Prazo (meses):"));
        JTextField txtPrazo = new JTextField(15);
        areaPrazo.add(txtPrazo);
                
        painelCamposEntrada.add(areaPrazo);

        painel.add(painelCamposEntrada);

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

        JPanel painelBotoes = new JPanel(new FlowLayout());
        
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);
                
        painel.add(painelBotoes);
        
        return painel;
    }

    public List<Cliente> listaClientes() {
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlCliente.getModel();
        List<Cliente> clientes = new ArrayList<>();
        
        for (int i = 0; i < model.size(); i++) {
            Cliente cliente = model.get(i);
            clientes.add(cliente);
        }
        
        int index = clientes.indexOf(usuarioLogado);
        clientes.remove(index);
        clientes.add(index, usuarioLogado);   

        return clientes;
    }

    public void carregaClientes(List<Cliente> clientes){
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlCliente.getModel();
        model.clear(); // Limpa a lista antes de adicionar os novos registros

        for (Cliente c : clientes) {
            model.addElement(c);
        }
    }
    
    public List<RendaFixa> listaRendasFixas(){
        DefaultListModel<RendaFixa> model = (DefaultListModel<RendaFixa>)jlRendasFixas.getModel();
        List<RendaFixa> rendas = new ArrayList<>();
        
        for (int i = 0; i < model.size(); i++) {
            RendaFixa rendaFixa = model.get(i);
            rendas.add(rendaFixa);
        }

        return rendas;
    }
    
    public void carregaRendasFixas(List<RendaFixa> rendasFixas) {
        DefaultListModel<RendaFixa> modelRendasFixas = (DefaultListModel<RendaFixa>) jlRendasFixas.getModel();
        modelRendasFixas.clear(); // Limpa a lista antes de adicionar os novos registros

        for (RendaFixa rf : rendasFixas) {
            modelRendasFixas.addElement(rf);
        }
    }
    
    public List<RendaVariavel> listaRendasVariaveis() {
        DefaultListModel<RendaVariavel> model = (DefaultListModel<RendaVariavel>) jlRendasVariaveis.getModel();
        List<RendaVariavel> rendas = new ArrayList<>();
        
        for (int i = 0; i < model.size(); i++) {
            RendaVariavel rendaVariavel = model.get(i);
            rendas.add(rendaVariavel);
        }

        return rendas;
    }
    
    public void carregaRendasVariaveis(List<RendaVariavel> rendas) {
        DefaultListModel<RendaVariavel> modelRendaVariavel = (DefaultListModel<RendaVariavel>) jlRendasVariaveis.getModel();
        modelRendaVariavel.clear(); // Limpa a lista antes de adicionar os novos registros

        for (RendaVariavel r : rendas) {
            modelRendaVariavel.addElement(r);
        }
    }
    
    public void carregaMeusInvestimentos(List<Investimento> investimentos) {
        DefaultListModel<Investimento> modelInvestimento = (DefaultListModel<Investimento>) jlMeusInvestimentos.getModel();
        modelInvestimento.clear(); // Limpa a lista antes de adicionar os novos registros

        for (Investimento i : investimentos) {
            modelInvestimento.addElement(i);
        }
    }
    
    // Método para filtrar investimentos disponíveis
    public List<RendaFixa> getRendasFixasDisponiveis() {
                
        List<RendaFixa> rendasFixasDisponiveis = new ArrayList<>();
        
        DefaultListModel<RendaFixa> model = (DefaultListModel<RendaFixa>)jlRendasFixas.getModel();
        List<RendaFixa> rendas = new ArrayList<>();
        
        for (int i = 0; i < model.size(); i++) {
            RendaFixa rendaFixa = model.get(i);
            rendas.add(rendaFixa);
        }
                
        for (RendaFixa rendaFixa : rendas) {
            if (!usuarioLogado.getInvestimentos().contains(rendaFixa)) {
                rendasFixasDisponiveis.add(rendaFixa);
            }
        }
        
        return rendasFixasDisponiveis;
    }

    // Método para filtrar investimentos disponíveis
    public List<RendaVariavel> getRendasVariaveisDisponiveis() {
        List<RendaVariavel> rendasVariaveisDisponiveis = new ArrayList<>();
                
        for (RendaVariavel rendaVariavel : listaRendasVariaveis()) {
            if (!usuarioLogado.getInvestimentos().contains(rendaVariavel)) {
                rendasVariaveisDisponiveis.add(rendaVariavel);
            }
        }
        
        return rendasVariaveisDisponiveis;
    }

    // Método para atualizar a interface após um investimento ou cancelamento
    public void atualizarInterface() {
        // Atualiza a lista de investimentos disponíveis
        DefaultListModel<RendaFixa> modelRendaFixa = (DefaultListModel<RendaFixa>) jlRendasFixas.getModel();

        // Cria uma cópia da lista de rendas fixas para evitar ConcurrentModificationException
        List<RendaFixa> rendasFixas = new ArrayList<>(getRendasFixasDisponiveis());
        
        for (RendaFixa rendaFixa : rendasFixas) {
            if(!modelRendaFixa.contains(rendaFixa)) {
                modelRendaFixa.addElement(rendaFixa);
            }
        }

        DefaultListModel<RendaVariavel> modelRendaVariavel = (DefaultListModel<RendaVariavel>) jlRendasVariaveis.getModel();

        // Cria uma cópia da lista de rendas variáveis para evitar ConcurrentModificationException
        List<RendaVariavel> rendasVariaveis = new ArrayList<>(getRendasVariaveisDisponiveis());
        
        for (RendaVariavel rendaVariavel : rendasVariaveis) {
            if(!modelRendaVariavel.contains(rendaVariavel)) {
                modelRendaVariavel.addElement(rendaVariavel);
            }
        }

        // Atualiza a lista de "Meus Investimentos"
        DefaultListModel<String> modelMeusInvestimentos = (DefaultListModel<String>) ((JList<String>) ((JScrollPane) ((JPanel) ((JTabbedPane) principal.getComponent(0)).getComponent(5)).getComponent(0)).getViewport().getView()).getModel();

        for (Investimento investimento : usuarioLogado.getInvestimentos()) {
            if (!modelMeusInvestimentos.contains(investimento)) { 
                modelMeusInvestimentos.addElement(investimento.toString());
            }
        }
         
        // Atualiza o saldo exibido
        // Acessa o JTabbedPane
        JTabbedPane tabbedPane = (JTabbedPane) principal.getComponent(0);

        // Acessa o segundo JPanel dentro do JTabbedPane
        JPanel segundoPanel = (JPanel) tabbedPane.getComponent(1);

        // Acessa o JLabel que exibe o saldo
        JLabel lblSaldo = (JLabel) segundoPanel.getComponent(0);
        lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getSaldo());

        // Acessa o JScrollPane que contém a JTable do extrato
        JScrollPane scrollPane = (JScrollPane) segundoPanel.getComponent(1);

        // Acessa a JTable dentro do JScrollPane
        JTable tabelaExtrato = (JTable) scrollPane.getViewport().getView();

        // Atualiza o extrato
        // Obtém o modelo da tabela
        DefaultTableModel modelExtrato = (DefaultTableModel) tabelaExtrato.getModel();
        modelExtrato.setRowCount(0); // Limpa o extrato antes de adicionar os novos registros

        // Adiciona as transações ao extrato
        for (Transacao transacao : usuarioLogado.getExtrato()) {
            modelExtrato.addRow(new Object[]{
                transacao.getDataHora(),
                transacao.getDescricao(),
                transacao.getValor()
            });
        }
    }
    
    public boolean atualizaUsuarioLogadoInvestimentoRendaFixa(RendaFixa rendaFixa, double valor) {
        return this.usuarioLogado.investirRendaFixa(rendaFixa, valor);
    }
    
    public boolean atualizaUsuarioLogadoInvestimentoRendaVariavel(RendaVariavel rendaVariavel, double valor) {
        return this.usuarioLogado.investirRendaVariavel(rendaVariavel, valor);
    }
    
    private Cliente buscaConta(String numeroConta) throws CpfException {
               
        List<Cliente> clientes = listaClientes();
        Cpf cpf = new Cpf(numeroConta);
        
        for (int i = 0; i < clientes.size(); i++) {            
            if(clientes.get(i).getCpf().equals(cpf)) {
                return clientes.get(i);
            }
        }

        return null;
    }
}
