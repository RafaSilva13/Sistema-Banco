package com.mycompany.view;

import com.mycompany.models.ClassesAuxiliares.*;
import javax.swing.border.EmptyBorder;
import com.mycompany.persistences.*;
import com.mycompany.models.Conta.*;
import com.mycompany.exceptions.*;
import com.mycompany.eventos.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Tela Login
public class TelaLogin extends JFrame {
    
    private JFrame tela;
    private JTabbedPane barraLogin;
    private JPanel principal;
    private final int WIDTH = 800;
    private final int HEIGHT = 400;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    
    // JTextField Login Clientes
    private JTextField tfCpfClientes;
    private JTextField tfSenhaClientes;
    
    // JTextField Login Gerentes
    private JTextField tfCpfGerenteLogin;
    private JTextField tfSenhaGerente;

    // JTextField Login Caixas
    private JTextField tfCpfCaixaLogin;
    private JTextField tfSenhaCaixa;
   
    // JTextField Login Administrador
    private JTextField tfUsuarioAdministrador;
    private JTextField tfSenhaAdministrador;
    
    // JTextField Cadastro Cliente
    private JTextField tfNomeCliente;
    private JTextField tfCpfCliente;
    private JTextField tfTelefoneCliente;
    private JTextField tfEmailCliente;
    private JTextField tfSenhaCadastroCliente;
   
    // JTextField Cadastro Gerente
    private JTextField tfNomeGerente;
    private JTextField tfCpfGerente;
    private JTextField tfTelefoneGerente;
    private JTextField tfEmailGerente;
    private JTextField tfSenhaCadastroGerente;

    // JTextField Cadastro Caixa
    private JTextField tfNomeCaixa;
    private JTextField tfCpfCaixa;
    private JTextField tfTelefoneCaixa;
    private JTextField tfEmailCaixa;
    private JTextField tfSenhaCadastroCaixa;
    
    // JLists
    private JList<Cliente> jlClientes;
    private JList<Gerente> jlGerentes;
    private JList<Caixa> jlCaixas;
    
    public void exibirTelaLogin() {
        
        // Cria uma nova janela
        tela = new JFrame("Sistema Banco");
        tela.addWindowListener(new GerenciaClientes(this));
        tela.addWindowListener(new GerenciaGerente(this));
        tela.addWindowListener(new GerenciaCaixa(this));
        
        // Define o excerramento do programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
        // Inicial o painel geral de login com um BorderLayout
        principal = new JPanel();
        principal.setLayout(new BorderLayout());

        DefaultListModel<Cliente> modelCliente = new DefaultListModel<>();
        jlClientes = new JList<>(modelCliente);
        
        DefaultListModel<Gerente> modelGerente = new DefaultListModel<>();
        jlGerentes = new JList<>(modelGerente);
        
        DefaultListModel<Caixa> modelCaixa = new DefaultListModel<>();
        jlCaixas = new JList<>(modelCaixa);
        
        // Inicia a barra superior de seleção de tipo de login com as opções
        barraLogin = new JTabbedPane();
        barraLogin.addTab("Cliente", criarPainelEntradaCliente());
        barraLogin.addTab("Gerente", criarPainelEntradaGerente());
        barraLogin.addTab("Caixa", criarPainelEntradaCaixa());
        barraLogin.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // Adiciona a barra superior ao painel geral
        principal.add(barraLogin);
       
        // Adiciona o painel geral a janela
        tela.add(principal);

        // Define o tamanho do painel geral e sua posição como central
        tela.setSize(WIDTH, 300);
        tela.setLocationRelativeTo(null);
        
        // Bloqueia o redimensionamento da janela
        tela.setResizable(false);
        
        // Deixa o painel visível
        tela.setVisible(true);
        
        tela.pack();
    }
    
    
    // Métodos de get para os campos de login
    public String getCpfCliente() {
        return tfCpfClientes.getText();
    }
    
    public String getSenhaCliente() {
        return tfSenhaClientes.getText();
    }
    
    public String getCpfGerente() {
        return tfCpfGerenteLogin.getText();
    }
    
    public String getSenhaGerente() {
        return tfSenhaGerente.getText();
    }
    
    public String getCpfCaixa() {
        return tfCpfCaixaLogin.getText();
    }
    
    public String getSenhaCaixa() {
        return tfSenhaCaixa.getText();
    }
    
    public String getUsuarioAdministrador() {
        return tfUsuarioAdministrador.getText();
    }
    
    public String getSenhaAdministrador() {
        return tfSenhaAdministrador.getText();
    }
    
    // Métodos de get para as JLists
    public JList getJListClientes() {
        return jlClientes;
    }
    
    public JList getJListGerentes() {
        return jlGerentes;
    }
    
    public JList getJListCaixas() {
        return jlCaixas;
    }
    
    private JPanel criarPainelEntradaCliente() {
        JPanel painelCliente = new JPanel();
        
        JPanel painelComumLogin = criaPainelClienteLogin();
        painelComumLogin.setSize(250, HEIGHT);
        painelComumLogin.setLayout(new BoxLayout(painelComumLogin, BoxLayout.Y_AXIS));
        painelComumLogin.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel painelBotaoLogin = new JPanel();
        painelBotaoLogin.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.addActionListener(new BotaoLoginCliente(tela, new TelaCliente(), this));

        painelBotaoLogin.add(botaoLogin);
        painelComumLogin.add(painelBotaoLogin);
        
        JPanel painelComumCadastro = criaPainelComumCadastroCliente();
        painelComumCadastro.setSize(550, HEIGHT);
        painelComumCadastro.setBorder(BorderFactory.createRaisedBevelBorder());

        painelCliente.add(painelComumLogin, BorderLayout.WEST);
        painelCliente.add(painelComumCadastro, BorderLayout.EAST);
        
        painelCliente.setBorder(new EmptyBorder(25, 0, 25, 0));
        
        return painelCliente;
    }

    private JPanel criarPainelEntradaGerente() {
        JPanel painelGerente = new JPanel();

        JPanel painelComumLogin = criaPainelGerenteLogin();
        painelComumLogin.setSize(250, HEIGHT);
        painelComumLogin.setLayout(new BoxLayout(painelComumLogin, BoxLayout.Y_AXIS));
        painelComumLogin.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel painelBotaoLogin = new JPanel();
        painelBotaoLogin.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.addActionListener(new BotaoLoginGerente(tela, new TelaGerente(), this));

        painelBotaoLogin.add(botaoLogin);
        painelComumLogin.add(painelBotaoLogin);

        JPanel painelComumCadastro = criaPainelComumCadastroGerente();
        painelComumCadastro.setSize(550, HEIGHT);
        painelComumCadastro.setBorder(BorderFactory.createRaisedBevelBorder());

        painelGerente.add(painelComumLogin, BorderLayout.WEST);
        painelGerente.add(painelComumCadastro, BorderLayout.EAST);

        painelGerente.setBorder(new EmptyBorder(25, 0, 25, 0));

        return painelGerente;
    }
    
    private JPanel criarPainelEntradaCaixa() {
        JPanel painelCaixa = new JPanel();

        JPanel painelComumLogin = criaPainelCaixaLogin();
        painelComumLogin.setSize(250, HEIGHT);
        painelComumLogin.setLayout(new BoxLayout(painelComumLogin, BoxLayout.Y_AXIS));
        painelComumLogin.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel painelBotaoLogin = new JPanel();
        painelBotaoLogin.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.addActionListener(new BotaoLoginCaixa(tela, new TelaCaixa(), this));

        painelBotaoLogin.add(botaoLogin);
        painelComumLogin.add(painelBotaoLogin);

        JPanel painelComumCadastro = criaPainelComumCadastroCaixa();
        painelComumCadastro.setSize(550, HEIGHT);
        painelComumCadastro.setBorder(BorderFactory.createRaisedBevelBorder());

        painelCaixa.add(painelComumLogin, BorderLayout.WEST);
        painelCaixa.add(painelComumCadastro, BorderLayout.EAST);

        painelCaixa.setBorder(new EmptyBorder(25, 0, 25, 0));

        return painelCaixa;
    }
    
    // Métodos para criar painéis de cadastro
    private JPanel criaPainelComumCadastroCliente() {
        JPanel areaCadastro = new JPanel();
        areaCadastro.setLayout(new BorderLayout());

        JPanel areaTextoCadastro = new JPanel();
        JLabel textoCadastro = new JLabel("Cadastro");
        Font fonte = new Font("Arial", Font.PLAIN, 18);
        textoCadastro.setFont(fonte);
        textoCadastro.setBorder(new EmptyBorder(10, 0, 8, 0));
        textoCadastro.setHorizontalTextPosition(JLabel.CENTER);
        areaTextoCadastro.add(textoCadastro);

        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.Y_AXIS));

        JPanel areaNomeCliente = new JPanel();
        areaNomeCliente.setBorder(BorderFactory.createTitledBorder("Nome"));
        tfNomeCliente = new JTextField(12);
        areaNomeCliente.add(tfNomeCliente);

        JPanel areaCpfCliente = new JPanel();
        areaCpfCliente.setBorder(BorderFactory.createTitledBorder("CPF"));
        tfCpfCliente = new JTextField(12);
        areaCpfCliente.add(tfCpfCliente);

        panelEsquerdo.add(areaNomeCliente);
        panelEsquerdo.add(areaCpfCliente);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JPanel areaTelefoneCliente = new JPanel();
        areaTelefoneCliente.setBorder(BorderFactory.createTitledBorder("Telefone"));
        tfTelefoneCliente = new JTextField(12);
        areaTelefoneCliente.add(tfTelefoneCliente);

        JPanel areaEmailCliente = new JPanel();
        areaEmailCliente.setBorder(BorderFactory.createTitledBorder("Email"));
        tfEmailCliente = new JTextField(12);
        areaEmailCliente.add(tfEmailCliente);

        panelCentral.add(areaTelefoneCliente);
        panelCentral.add(areaEmailCliente);

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.Y_AXIS));

        // Adicionando o campo de senha
        JPanel areaSenhaCliente = new JPanel();
        areaSenhaCliente.setBorder(BorderFactory.createTitledBorder("Senha"));
        tfSenhaCadastroCliente = new JPasswordField(12); // Usando JPasswordField para senha
        areaSenhaCliente.add(tfSenhaCadastroCliente);

        JPanel painelBotaoCadastro = new JPanel();
        painelBotaoCadastro.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.addActionListener(new AdicionarCliente(this));
        painelBotaoCadastro.add(botaoCadastro);

        panelDireito.add(areaSenhaCliente);

        areaCadastro.add(areaTextoCadastro, BorderLayout.NORTH);
        areaCadastro.add(panelEsquerdo, BorderLayout.WEST);
        areaCadastro.add(panelCentral, BorderLayout.CENTER);
        areaCadastro.add(panelDireito, BorderLayout.EAST);
        areaCadastro.add(painelBotaoCadastro, BorderLayout.SOUTH);

        return areaCadastro;
    }
    
    private JPanel criaPainelComumCadastroGerente() {
        JPanel areaCadastro = new JPanel();
        areaCadastro.setLayout(new BorderLayout());

        JPanel areaTextoCadastro = new JPanel();
        JLabel textoCadastro = new JLabel("Cadastro");
        Font fonte = new Font("Arial", Font.PLAIN, 18);
        textoCadastro.setFont(fonte);
        textoCadastro.setBorder(new EmptyBorder(10, 0, 8, 0));
        textoCadastro.setHorizontalTextPosition(JLabel.CENTER);
        areaTextoCadastro.add(textoCadastro);

        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.Y_AXIS));

        JPanel areaNomeGerente = new JPanel();
        areaNomeGerente.setBorder(BorderFactory.createTitledBorder("Nome"));
        tfNomeGerente = new JTextField(12);
        areaNomeGerente.add(tfNomeGerente);

        JPanel areaCpfGerente = new JPanel();
        areaCpfGerente.setBorder(BorderFactory.createTitledBorder("CPF"));
        tfCpfGerente = new JTextField(12);
        areaCpfGerente.add(tfCpfGerente);

        panelEsquerdo.add(areaNomeGerente);
        panelEsquerdo.add(areaCpfGerente);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JPanel areaTelefoneGerente = new JPanel();
        areaTelefoneGerente.setBorder(BorderFactory.createTitledBorder("Telefone"));
        tfTelefoneGerente = new JTextField(12);
        areaTelefoneGerente.add(tfTelefoneGerente);

        JPanel areaEmailGerente = new JPanel();
        areaEmailGerente.setBorder(BorderFactory.createTitledBorder("Email"));
        tfEmailGerente = new JTextField(12);
        areaEmailGerente.add(tfEmailGerente);

        panelCentral.add(areaTelefoneGerente);
        panelCentral.add(areaEmailGerente);

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.Y_AXIS));

        // Adicionando o campo de senha
        JPanel areaSenhaGerente = new JPanel();
        areaSenhaGerente.setBorder(BorderFactory.createTitledBorder("Senha"));
        tfSenhaCadastroGerente = new JPasswordField(12); // Usando JPasswordField para senha
        areaSenhaGerente.add(tfSenhaCadastroGerente);

        JPanel painelBotaoCadastro = new JPanel();
        painelBotaoCadastro.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.addActionListener(new AdicionarGerente(this));
        painelBotaoCadastro.add(botaoCadastro);

        panelDireito.add(areaSenhaGerente);

        areaCadastro.add(areaTextoCadastro, BorderLayout.NORTH);
        areaCadastro.add(panelEsquerdo, BorderLayout.WEST);
        areaCadastro.add(panelCentral, BorderLayout.CENTER);
        areaCadastro.add(panelDireito, BorderLayout.EAST);
        areaCadastro.add(painelBotaoCadastro, BorderLayout.SOUTH);

        return areaCadastro;
    }
    
    private JPanel criaPainelComumCadastroCaixa() {
        JPanel areaCadastro = new JPanel();
        areaCadastro.setLayout(new BorderLayout());

        JPanel areaTextoCadastro = new JPanel();
        JLabel textoCadastro = new JLabel("Cadastro");
        Font fonte = new Font("Arial", Font.PLAIN, 18);
        textoCadastro.setFont(fonte);
        textoCadastro.setBorder(new EmptyBorder(10, 0, 8, 0));
        textoCadastro.setHorizontalTextPosition(JLabel.CENTER);
        areaTextoCadastro.add(textoCadastro);

        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.Y_AXIS));

        JPanel areaNomeCaixa = new JPanel();
        areaNomeCaixa.setBorder(BorderFactory.createTitledBorder("Nome"));
        tfNomeCaixa = new JTextField(12);
        areaNomeCaixa.add(tfNomeCaixa);

        JPanel areaCpfCaixa = new JPanel();
        areaCpfCaixa.setBorder(BorderFactory.createTitledBorder("CPF"));
        tfCpfCaixa = new JTextField(12);
        areaCpfCaixa.add(tfCpfCaixa);

        panelEsquerdo.add(areaNomeCaixa);
        panelEsquerdo.add(areaCpfCaixa);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JPanel areaTelefoneCaixa = new JPanel();
        areaTelefoneCaixa.setBorder(BorderFactory.createTitledBorder("Telefone"));
        tfTelefoneCaixa = new JTextField(12);
        areaTelefoneCaixa.add(tfTelefoneCaixa);

        JPanel areaEmailCaixa = new JPanel();
        areaEmailCaixa.setBorder(BorderFactory.createTitledBorder("Email"));
        tfEmailCaixa = new JTextField(12);
        areaEmailCaixa.add(tfEmailCaixa);

        panelCentral.add(areaTelefoneCaixa);
        panelCentral.add(areaEmailCaixa);

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.Y_AXIS));

        // Adicionando o campo de senha
        JPanel areaSenhaCaixa = new JPanel();
        areaSenhaCaixa.setBorder(BorderFactory.createTitledBorder("Senha"));
        tfSenhaCadastroCaixa = new JPasswordField(12); // Usando JPasswordField para senha
        areaSenhaCaixa.add(tfSenhaCadastroCaixa);

        JPanel painelBotaoCadastro = new JPanel();
        painelBotaoCadastro.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.addActionListener(new AdicionarCaixa(this));
        painelBotaoCadastro.add(botaoCadastro);

        panelDireito.add(areaSenhaCaixa);

        areaCadastro.add(areaTextoCadastro, BorderLayout.NORTH);
        areaCadastro.add(panelEsquerdo, BorderLayout.WEST);
        areaCadastro.add(panelCentral, BorderLayout.CENTER);
        areaCadastro.add(panelDireito, BorderLayout.EAST);
        areaCadastro.add(painelBotaoCadastro, BorderLayout.SOUTH);

        return areaCadastro;
    }
    
    public JPanel criaPainelAdministradorLogin() {
        // Painel principal de login
        JPanel areaLogin = new JPanel();
        areaLogin.setLayout(new GridLayout(0, 1, H_GAP, V_GAP)); // Layout em grade com espaçamento

        // Área de texto "Login"
        JPanel areaTextoLogin = new JPanel();
        JLabel textoLogin = new JLabel("Login");
        Font fonte = new Font("Arial", Font.PLAIN, 20); // Define a fonte do texto
        textoLogin.setFont(fonte);
        textoLogin.setBorder(new EmptyBorder(8, 0, 8, 0)); // Define borda superior e inferior
        textoLogin.setHorizontalTextPosition(JLabel.CENTER); // Centraliza o texto
        areaTextoLogin.add(textoLogin); // Adiciona o texto à área de texto
        areaLogin.add(areaTextoLogin); // Adiciona a área de texto ao painel de login

        // Área de inserção do Usuário
        JPanel areaUsuario = new JPanel();
        areaUsuario.setBorder(BorderFactory.createTitledBorder("Usuário")); // Título da área
        tfUsuarioAdministrador = new JTextField(15); // Campo de texto para o usuário
        areaUsuario.add(tfUsuarioAdministrador); // Adiciona o campo de texto à área
        areaLogin.add(areaUsuario); // Adiciona a área de usuário ao painel de login

        // Área de inserção da Senha
        JPanel areaSenha = new JPanel();
        areaSenha.setBorder(BorderFactory.createTitledBorder("Senha")); // Título da área
        tfSenhaAdministrador = new JTextField(15); // Campo de texto para a senha
        areaSenha.add(tfSenhaAdministrador); // Adiciona o campo de texto à área
        areaLogin.add(areaSenha); // Adiciona a área de senha ao painel de login

        return areaLogin; // Retorna o painel de login completo
    }
    
    public JPanel criaPainelClienteLogin() {
        // Painel principal de login
        JPanel areaLogin = new JPanel();
        areaLogin.setLayout(new GridLayout(0, 1, H_GAP, V_GAP)); // Layout em grade com espaçamento

        // Área de texto "Login"
        JPanel areaTextoLogin = new JPanel();
        JLabel textoLogin = new JLabel("Login");
        Font fonte = new Font("Arial", Font.PLAIN, 20); // Define a fonte do texto
        textoLogin.setFont(fonte);
        textoLogin.setBorder(new EmptyBorder(8, 0, 8, 0)); // Define borda superior e inferior
        textoLogin.setHorizontalTextPosition(JLabel.CENTER); // Centraliza o texto
        areaTextoLogin.add(textoLogin); // Adiciona o texto à área de texto
        areaLogin.add(areaTextoLogin); // Adiciona a área de texto ao painel de login

        // Área de inserção do CPF
        JPanel areaCpf = new JPanel();
        areaCpf.setBorder(BorderFactory.createTitledBorder("CPF")); // Título da área
        tfCpfClientes = new JTextField(15); // Campo de texto para o CPF
        areaCpf.add(tfCpfClientes); // Adiciona o campo de texto à área
        areaLogin.add(areaCpf); // Adiciona a área de CPF ao painel de login

        // Área de inserção da Senha
        JPanel areaSenha = new JPanel();
        areaSenha.setBorder(BorderFactory.createTitledBorder("Senha")); // Título da área
        tfSenhaClientes = new JTextField(15); // Campo de texto para a senha
        areaSenha.add(tfSenhaClientes); // Adiciona o campo de texto à área
        areaLogin.add(areaSenha); // Adiciona a área de senha ao painel de login

        return areaLogin; // Retorna o painel de login completo
    }
    
    public JPanel criaPainelGerenteLogin() {
        JPanel areaLogin = new JPanel();
        areaLogin.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        JPanel areaTextoLogin = new JPanel();
        JLabel textoLogin = new JLabel("Login");
        Font fonte = new Font("Arial", Font.PLAIN, 20);
        textoLogin.setFont(fonte);
        textoLogin.setBorder(new EmptyBorder(8, 0, 8, 0));
        textoLogin.setHorizontalTextPosition(JLabel.CENTER);
        areaTextoLogin.add(textoLogin);
        areaLogin.add(areaTextoLogin);

        JPanel areaCpf = new JPanel();
        areaCpf.setBorder(BorderFactory.createTitledBorder("CPF"));
        tfCpfGerenteLogin = new JTextField(15);
        areaCpf.add(tfCpfGerenteLogin);
        areaLogin.add(areaCpf);

        JPanel areaSenha = new JPanel();
        areaSenha.setBorder(BorderFactory.createTitledBorder("Senha"));
        tfSenhaGerente = new JTextField(15);
        areaSenha.add(tfSenhaGerente);
        areaLogin.add(areaSenha);

        return areaLogin;
    }
    
    public JPanel criaPainelCaixaLogin() {
        JPanel areaLogin = new JPanel();
        areaLogin.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        JPanel areaTextoLogin = new JPanel();
        JLabel textoLogin = new JLabel("Login");
        Font fonte = new Font("Arial", Font.PLAIN, 20);
        textoLogin.setFont(fonte);
        textoLogin.setBorder(new EmptyBorder(8, 0, 8, 0));
        textoLogin.setHorizontalTextPosition(JLabel.CENTER);
        areaTextoLogin.add(textoLogin);
        areaLogin.add(areaTextoLogin);

        JPanel areaCpf = new JPanel();
        areaCpf.setBorder(BorderFactory.createTitledBorder("CPF"));
        tfCpfCaixaLogin = new JTextField(15);
        areaCpf.add(tfCpfCaixaLogin);
        areaLogin.add(areaCpf);

        JPanel areaSenha = new JPanel();
        areaSenha.setBorder(BorderFactory.createTitledBorder("Senha"));
        tfSenhaCaixa = new JTextField(15);
        areaSenha.add(tfSenhaCaixa);
        areaLogin.add(areaSenha);

        return areaLogin;
    }
    
    // Métodos para adicionar Cliente, Gerente e Caixa
    public void addCliente() {
        DefaultListModel<Cliente> modelClientes = (DefaultListModel<Cliente>)jlClientes.getModel();
        
        try {
            if (tfNomeCliente.getText().length() != 0 && tfCpfCliente.getText().length() != 0 && tfTelefoneCliente.getText().length() != 0 && tfEmailCliente.getText().length() != 0 && tfSenhaCadastroCliente.getText().length() != 0) {
                
                Cliente novoCliente = new Cliente(tfNomeCliente.getText(), new Cpf(tfCpfCliente.getText()), new Telefone(tfTelefoneCliente.getText()), new Email(tfEmailCliente.getText()), tfSenhaCadastroCliente.getText());
                
                List<Cliente> clientes = new ArrayList<>();

                for (int i = 0; i < modelClientes.size(); i++) {
                    clientes.add(modelClientes.get(i));
                }
                
                if (!clientes.contains(novoCliente)) {
                    modelClientes.addElement(novoCliente);
                    Persistence<Cliente> clientePersistence = new ClientePersistence();
                    clientePersistence.save(listaClientes());
                    JOptionPane.showMessageDialog(tela, "Cadastro realizado com sucesso!");
                    
                    tfNomeCliente.setText("");
                    tfCpfCliente.setText("");
                    tfTelefoneCliente.setText("");
                    tfEmailCliente.setText("");
                    tfSenhaCadastroCliente.setText("");
                } else {
                    JOptionPane.showMessageDialog(tela, "Cliente já existe!");
                }
            } else {
                JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
            }
        } catch (EmailException e) {
            JOptionPane.showMessageDialog(tela, "O email " + tfEmailCliente.getText() +" é invalido!");
        } catch (TelefoneException e) {
            JOptionPane.showMessageDialog(tela, "O telefone " + tfTelefoneCliente.getText() +" é invalido!");
        } catch (CpfException e) {
            JOptionPane.showMessageDialog(tela, "O CPF " + tfCpfCliente.getText() +" é invalido!");
        }
    }
    
    public void addGerente() {
        DefaultListModel<Gerente> modelGerente = (DefaultListModel<Gerente>) jlGerentes.getModel();

        try {
            if (tfNomeGerente.getText().length() != 0 && tfCpfGerente.getText().length() != 0 && tfTelefoneGerente.getText().length() != 0 && tfEmailGerente.getText().length() != 0 && tfSenhaCadastroGerente.getText().length() != 0) {

                Gerente novoGerente = new Gerente(tfNomeGerente.getText(), new Cpf(tfCpfGerente.getText()), new Telefone(tfTelefoneGerente.getText()), new Email(tfEmailGerente.getText()), tfSenhaCadastroGerente.getText());

                List<Gerente> gerentes = new ArrayList<>();

                for (int i = 0; i < modelGerente.size(); i++) {
                    gerentes.add(modelGerente.get(i));
                }

                if (!gerentes.contains(novoGerente)) {
                    modelGerente.addElement(novoGerente);
                    Persistence<Gerente> gerentePersistence = new GerentePersistence();
                    gerentePersistence.save(listaGerentes());
                    JOptionPane.showMessageDialog(tela, "Cadastro realizado com sucesso!");

                    tfNomeGerente.setText("");
                    tfCpfGerente.setText("");
                    tfTelefoneGerente.setText("");
                    tfEmailGerente.setText("");
                    tfSenhaCadastroGerente.setText("");
                } else {
                    JOptionPane.showMessageDialog(tela, "Gerente já existe!");
                }
            } else {
                JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
            }
        } catch (EmailException e) {
            JOptionPane.showMessageDialog(tela, "O email " + tfEmailGerente.getText() + " é inválido!");
        } catch (TelefoneException e) {
            JOptionPane.showMessageDialog(tela, "O telefone " + tfTelefoneGerente.getText() + " é inválido!");
        } catch (CpfException e) {
            JOptionPane.showMessageDialog(tela, "O CPF " + tfCpfGerente.getText() + " é inválido!");
        }
    }

    public void addCaixa() {
        DefaultListModel<Caixa> modelCaixa = (DefaultListModel<Caixa>) jlCaixas.getModel();

        try {
            if (tfNomeCaixa.getText().length() != 0 && tfCpfCaixa.getText().length() != 0 && tfTelefoneCaixa.getText().length() != 0 && tfEmailCaixa.getText().length() != 0 && tfSenhaCadastroCaixa.getText().length() != 0) {

                Caixa novoCaixa = new Caixa(tfNomeCaixa.getText(), new Cpf(tfCpfCaixa.getText()), new Telefone(tfTelefoneCaixa.getText()), new Email(tfEmailCaixa.getText()), tfSenhaCadastroCaixa.getText());

                List<Caixa> caixas = new ArrayList<>();

                for (int i = 0; i < modelCaixa.size(); i++) {
                    caixas.add(modelCaixa.get(i));
                }

                if (!caixas.contains(novoCaixa)) {
                    modelCaixa.addElement(novoCaixa);
                    Persistence<Caixa> caixaPersistence = new CaixaPersistence();
                    caixaPersistence.save(listaCaixas());
                    JOptionPane.showMessageDialog(tela, "Cadastro realizado com sucesso!");

                    tfNomeCaixa.setText("");
                    tfCpfCaixa.setText("");
                    tfTelefoneCaixa.setText("");
                    tfEmailCaixa.setText("");
                    tfSenhaCadastroCaixa.setText("");
                } else {
                    JOptionPane.showMessageDialog(tela, "Caixa já existe!");
                }
            } else {
                JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
            }
        } catch (EmailException e) {
            JOptionPane.showMessageDialog(tela, "O email " + tfEmailCaixa.getText() + " é inválido!");
        } catch (TelefoneException e) {
            JOptionPane.showMessageDialog(tela, "O telefone " + tfTelefoneCaixa.getText() + " é inválido!");
        } catch (CpfException e) {
            JOptionPane.showMessageDialog(tela, "O CPF " + tfCpfCaixa.getText() + " é inválido!");
        }
    }
   
    public void carregaClientes(List<Cliente> clientes) {
        DefaultListModel<Cliente> modelCliente = (DefaultListModel<Cliente>) jlClientes.getModel();

        for (Cliente c : clientes) {
            modelCliente.addElement(c);
        }
    }
    
    public List<Cliente> listaClientes() {
        DefaultListModel<Cliente> modelCliente = (DefaultListModel<Cliente>) jlClientes.getModel();
        List<Cliente> clientes = new ArrayList<>();

        for (int i = 0; i < modelCliente.size(); i++) {
            clientes.add(modelCliente.get(i));
        }

        return clientes;
    }
    
    public void carregaGerentes(List<Gerente> gerentes) {
        DefaultListModel<Gerente> modelGerente = (DefaultListModel<Gerente>) jlGerentes.getModel();

        for (Gerente g : gerentes) {
            modelGerente.addElement(g);
        }
    }
    
    public List<Gerente> listaGerentes() {
        DefaultListModel<Gerente> modelGerente = (DefaultListModel<Gerente>) jlGerentes.getModel();
        List<Gerente> gerentes = new ArrayList<>();

        for (int i = 0; i < modelGerente.size(); i++) {
            gerentes.add(modelGerente.get(i));
        }

        return gerentes;
    }
    
    public void carregaCaixas(List<Caixa> caixas) {
        DefaultListModel<Caixa> modelCaixas = (DefaultListModel<Caixa>) jlCaixas.getModel();

        for (Caixa c : caixas) {
            modelCaixas.addElement(c);
        }
    }
    
    public List<Caixa> listaCaixas() {
        DefaultListModel<Caixa> modelCaixa = (DefaultListModel<Caixa>) jlCaixas.getModel();
        List<Caixa> caixas = new ArrayList<>();

        for (int i = 0; i < modelCaixa.size(); i++) {
            caixas.add(modelCaixa.get(i));
        }

        return caixas;
    }
    
    private void limparCamposGerente() {
        tfNomeGerente.setText("");
        tfCpfGerente.setText("");
        tfTelefoneGerente.setText("");
        tfEmailGerente.setText("");
        tfSenhaCadastroGerente.setText("");
    }
    
    private void limparCamposCaixa() {
        tfNomeCaixa.setText("");    
        tfCpfCaixa.setText("");
        tfTelefoneCaixa.setText("");
        tfEmailCaixa.setText("");
        tfSenhaCadastroCaixa.setText("");
    }
    
    private boolean validarDadosCadastro(String nome, String cpf, String telefone, String email, String senha) {
        return nome.length() > 0 && cpf.length() > 0 && telefone.length() > 0 && email.length() > 0 && senha.length() > 0;
    }
}
