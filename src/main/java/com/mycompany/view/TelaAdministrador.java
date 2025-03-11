package com.mycompany.view;

import com.mycompany.models.ClassesAuxiliares.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import com.mycompany.models.Conta.*;
import com.mycompany.exceptions.*;
import java.awt.event.ActionEvent;
import com.mycompany.eventos.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Tela Administrador
public class TelaAdministrador {

    private JFrame tela;
    private JPanel principal;
    private final int WIDTH = 800;
    private final int HEIGHT = 400;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private JTextField tfNome;
    private JTextField tfEmail;
    private JTextField tfTelefone;
    private JTextField tfCpf;
    public String selectedItem = "";

    private JList<Cliente> jlCliente;
    private JList<Gerente> jlGerente;
    private JList<Caixa> jlCaixa;
    private JList<Transacao> jlTransacoes;

    public void exibirTelaAdministrador() {
        // Cria uma nova janela
        tela = new JFrame("Área Administrador");
       
        DefaultListModel<Transacao> modelTransacoes = new DefaultListModel<>();
        jlTransacoes = new JList<>(modelTransacoes);

        DefaultListModel<Cliente> modelClientes = new DefaultListModel<>();
        jlCliente = new JList<>(modelClientes);

        DefaultListModel<Gerente> modelGerentes = new DefaultListModel<>();
        jlGerente = new JList<>(modelGerentes);

        DefaultListModel<Caixa> modelCaixas = new DefaultListModel<>();
        jlCaixa = new JList<>(modelCaixas);

        tela.addWindowListener(new GerenciaTransacoesAdministrador(this));
        tela.addWindowListener(new GerenciaClientesAdministrador(this));
        tela.addWindowListener(new GerenciaGerentesAdministrador(this));
        tela.addWindowListener(new GerenciaCaixasAdministrador(this));

        // Define o fechamento do programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona o menu de Opções
        tela.setJMenuBar(criaBarraDeOpcoes());

        principal = new JPanel();
        principal.setLayout(new BorderLayout());

        this.exibirDadosGerais();

        // Adiciona o painel principal à janela
        tela.add(principal);

        // Define o tamanho do painel principal e sua posição como central
        tela.setSize(WIDTH, HEIGHT);
        tela.setLocationRelativeTo(null);

        // Bloqueia o redimensionamento da janela
        tela.setResizable(false);

        // Deixa o painel visível
        tela.setVisible(true);
    }

    private JMenuBar criaBarraDeOpcoes() {
        // Cria barra de menu de opções
        JMenuBar menuBar = new JMenuBar();

        // Cria um novo menu
        JMenu menu = new JMenu("Opções");

        JMenuItem item1 = new JMenuItem("Dados Gerais");
        item1.addActionListener(new OpcaoDadosGeraisAdministrador(this));

        JMenuItem item2 = new JMenuItem("Transações");
        item2.addActionListener(new OpcaoTransacoesAdministrador(this));

        // Adiciona opções no menu
        menu.add(item1);
        menu.addSeparator();
        menu.add(item2);

        // Adiciona menu na barra de menus
        menuBar.add(menu);

        return menuBar;
    }

    public void exibirDadosGerais() {
        // Remove o painel atual
        principal.removeAll();

        JPanel areaDadosGerais = new JPanel();
        areaDadosGerais.setLayout(new BorderLayout());
        areaDadosGerais.setPreferredSize(new Dimension(500, 200));

        //------------------------------------------------------------------------------

        String[] menuItems = {"Clientes", "Gerentes", "Caixas"};

        JPanel painelTipoUsuario = new JPanel();
        painelTipoUsuario.setBorder(BorderFactory.createTitledBorder("Tipo Usuário"));
        painelTipoUsuario.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT));
        painelTipoUsuario.setLayout(new BorderLayout());

        // Criando um DefaultListModel e adicionando os itens
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : menuItems) {
            listModel.addElement(item);
        }

        // Criando a JList com o DefaultListModel
        JList<String> menuList = new JList<>(listModel);

        painelTipoUsuario.add(new JScrollPane(menuList), BorderLayout.CENTER);

        //------------------------------------------------------------------------------

        JPanel painelUsuario = new JPanel();
        painelUsuario.setBorder(BorderFactory.createTitledBorder("Usuários"));
        painelUsuario.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT));
        painelUsuario.setLayout(new BorderLayout());

        //------------------------------------------------------------------------------

        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedItem = menuList.getSelectedValue();
                    exibirDadosGerais();
                }
            }
        });

        //------------------------------------------------------------------------------

        JPanel painelUsuarioDadosUsuario = new JPanel();
        painelUsuarioDadosUsuario.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        painelUsuarioDadosUsuario.setBorder(BorderFactory.createTitledBorder("Dados Usuário"));

        JPanel formulario = new JPanel();
        JPanel painelUsuarioDadosUsuarioLabel = new JPanel();
        painelUsuarioDadosUsuarioLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBorder(new EmptyBorder(2, 0, 2, 0));
        painelUsuarioDadosUsuarioLabel.add(lblNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBorder(new EmptyBorder(2, 0, 2, 0));
        painelUsuarioDadosUsuarioLabel.add(lblEmail);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBorder(new EmptyBorder(2, 0, 2, 0));
        painelUsuarioDadosUsuarioLabel.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBorder(new EmptyBorder(2, 0, 2, 0));
        painelUsuarioDadosUsuarioLabel.add(lblTelefone);

        //------------------------------------------------------------------------------

        JPanel painelUsuarioDadosUsuarioField = new JPanel();
        painelUsuarioDadosUsuarioField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        tfNome = new JTextField(15);
        tfEmail = new JTextField(15);
        tfCpf = new JTextField(15);
        tfTelefone = new JTextField(15);

        tfNome.setEditable(false);
        tfEmail.setEditable(false);
        tfCpf.setEditable(false);
        tfTelefone.setEditable(false);

        //------------------------------------------------------------------------------

        painelUsuarioDadosUsuarioField.add(tfNome);
        painelUsuarioDadosUsuarioField.add(tfEmail);
        painelUsuarioDadosUsuarioField.add(tfCpf);
        painelUsuarioDadosUsuarioField.add(tfTelefone);

        //------------------------------------------------------------------------------
        formulario.add(painelUsuarioDadosUsuarioLabel);
        formulario.add(painelUsuarioDadosUsuarioField);

        painelUsuarioDadosUsuario.setLayout(new BorderLayout());
        painelUsuarioDadosUsuario.add(formulario, BorderLayout.CENTER);

        //------------------------------------------------------------------------------

        JButton btnAdicionar = new JButton("Adicionar Usuário");
        btnAdicionar.addActionListener(new AdicionarUsuario(this));

        //------------------------------------------------------------------------------

        JButton btnEditar = new JButton("Editar Usuário");
        btnEditar.addActionListener(new EditarUsuario(this, selectedItem));

        //------------------------------------------------------------------------------

        JButton btnRemover = new JButton("Remover");

        //------------------------------------------------------------------------------

        if (selectedItem != null && !selectedItem.isEmpty()) {
            switch (selectedItem) {
                case "Clientes":
                    jlCliente.addListSelectionListener(new SelecionarContatoCliente(this));
                    painelUsuario.add(new JScrollPane(jlCliente), BorderLayout.CENTER);
                    btnRemover.addActionListener(new RemoverUsuario(this, selectedItem));
                    break;
                case "Gerentes":
                    jlGerente.addListSelectionListener(new SelecionarContatoGerente(this));
                    painelUsuario.add(new JScrollPane(jlGerente), BorderLayout.CENTER);
                    btnRemover.addActionListener(new RemoverUsuario(this, selectedItem));
                    break;
                case "Caixas":
                    jlCaixa.addListSelectionListener(new SelecionarContatoCaixa(this));
                    painelUsuario.add(new JScrollPane(jlCaixa), BorderLayout.CENTER);
                    btnRemover.addActionListener(new RemoverUsuario(this, selectedItem));
                    break;
            }
        }

        //------------------------------------------------------------------------------
        JPanel botoes = new JPanel(new FlowLayout());
        botoes.add(btnRemover);
        botoes.add(btnEditar); // Adiciona o botão "Editar Usuário"
        botoes.add(btnAdicionar);

        // Define um tamanho mínimo para o painel de botões
        botoes.setPreferredSize(new Dimension(300, 100));

        painelUsuarioDadosUsuario.add(botoes, BorderLayout.SOUTH);

        //------------------------------------------------------------------------------

        areaDadosGerais.add(painelTipoUsuario, BorderLayout.WEST);
        areaDadosGerais.add(painelUsuario, BorderLayout.CENTER);
        areaDadosGerais.add(painelUsuarioDadosUsuario, BorderLayout.EAST);

        // Adiciona os dados gerais à janela
        principal.add(areaDadosGerais);

        // Atualiza tela
        principal.revalidate();
        principal.repaint();
    }

    public void exibirFormularioCadastro() {
        JDialog dialog = new JDialog(tela, "Adicionar Usuário", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(new Dimension(WIDTH / 3, 300)); // Ajuste o tamanho conforme necessário
        dialog.setLocationRelativeTo(tela);

        JPanel formulario = new JPanel();
        formulario.setBorder(BorderFactory.createTitledBorder("Adicionar"));
        formulario.setLayout(new GridLayout(0, 2, H_GAP, V_GAP));

        // Campos do formulário lbl
        JPanel painelUsuarioDadosUsuarioLabel = new JPanel();
        painelUsuarioDadosUsuarioLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        JLabel lblTipoUsuario = new JLabel("Tipo de Usuário:");
        lblTipoUsuario.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblTipoUsuario);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblSenha);

        // Campos do formulário tf
        JPanel painelUsuarioDadosUsuarioField = new JPanel();
        painelUsuarioDadosUsuarioField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        // JComboBox para selecionar o tipo de usuário
        String[] tiposUsuario = {"Cliente", "Gerente", "Caixa"};
        JComboBox<String> cbTipoUsuario = new JComboBox<>(tiposUsuario);

        JTextField tfNome = new JTextField(15);
        JTextField tfCpf = new JTextField(15);
        JTextField tfTelefone = new JTextField(15);
        JTextField tfEmail = new JTextField(15);
        JTextField tfSenha = new JTextField(15);

        painelUsuarioDadosUsuarioField.add(cbTipoUsuario); // Adiciona o JComboBox ao formulário
        painelUsuarioDadosUsuarioField.add(tfNome);
        painelUsuarioDadosUsuarioField.add(tfCpf);
        painelUsuarioDadosUsuarioField.add(tfTelefone);
        painelUsuarioDadosUsuarioField.add(tfEmail);
        painelUsuarioDadosUsuarioField.add(tfSenha);

        formulario.add(painelUsuarioDadosUsuarioLabel);
        formulario.add(painelUsuarioDadosUsuarioField);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica para salvar o usuário
            String tipoUsuario = (String) cbTipoUsuario.getSelectedItem(); // Obtém o tipo de usuário selecionado
            String nome = tfNome.getText();
            String cpf = tfCpf.getText();
            String telefone = tfTelefone.getText();
            String email = tfEmail.getText();
            String senha = tfSenha.getText();

            if (validarDadosCadastro(nome, cpf, telefone, email, senha)) {
                switch (tipoUsuario) {
                    case "Cliente":
                        adicionarCliente(nome, cpf, telefone, email, senha);
                        break;
                    case "Gerente":
                        adicionarGerente(nome, cpf, telefone, email, senha);
                        break;
                    case "Caixa":
                        adicionarCaixa(nome, cpf, telefone, email, senha);
                        break;
                }
                dialog.dispose(); // Fecha o diálogo após salvar
            } else {
                JOptionPane.showMessageDialog(dialog, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);

        dialog.add(formulario, BorderLayout.CENTER);
        dialog.add(painelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    public void exibirFormularioEdicao(String tipoUsuario, int selectedIndex) {
        JDialog dialog = new JDialog(tela, "Editar " + tipoUsuario, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(new Dimension(WIDTH / 3, 250)); // Ajuste o tamanho conforme necessário
        dialog.setLocationRelativeTo(tela);

        JPanel formulario = new JPanel();
        formulario.setBorder(BorderFactory.createTitledBorder("Editar"));
        formulario.setLayout(new GridLayout(0, 2, H_GAP, V_GAP));

        // Campos do formulário lbl
        JPanel painelUsuarioDadosUsuarioLabel = new JPanel();
        painelUsuarioDadosUsuarioLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBorder(new EmptyBorder(2, 2, 1, 0));
        painelUsuarioDadosUsuarioLabel.add(lblSenha);

        // Campos do formulário tf
        JPanel painelUsuarioDadosUsuarioField = new JPanel();
        painelUsuarioDadosUsuarioField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        JTextField tfNome = new JTextField(15);
        JTextField tfCpf = new JTextField(15);
        JTextField tfTelefone = new JTextField(15);
        JTextField tfEmail = new JTextField(15);
        JTextField tfSenha = new JTextField(15);

        // Preenche os campos com os dados do usuário selecionado
        switch (tipoUsuario) {
            case "Clientes":
                Cliente cliente = ((DefaultListModel<Cliente>) jlCliente.getModel()).get(selectedIndex);
                tfNome.setText(cliente.getNome());
                tfCpf.setText(cliente.getCpf().toString());
                tfTelefone.setText(cliente.getNumeroDeTelefone().toString());
                tfEmail.setText(cliente.getEmail().toString());
                tfSenha.setText(cliente.getSenha());
                break;
            case "Gerentes":
                Gerente gerente = ((DefaultListModel<Gerente>) jlGerente.getModel()).get(selectedIndex);
                tfNome.setText(gerente.getNome());
                tfCpf.setText(gerente.getCpf().toString());
                tfTelefone.setText(gerente.getNumeroDeTelefone().toString());
                tfEmail.setText(gerente.getEmail().toString());
                tfSenha.setText(gerente.getSenha());
                break;
            case "Caixas":
                Caixa caixa = ((DefaultListModel<Caixa>) jlCaixa.getModel()).get(selectedIndex);
                tfNome.setText(caixa.getNome());
                tfCpf.setText(caixa.getCpf().toString());
                tfTelefone.setText(caixa.getNumeroDeTelefone().toString());
                tfEmail.setText(caixa.getEmail().toString());
                tfSenha.setText(caixa.getSenha());
                break;
        }

        painelUsuarioDadosUsuarioField.add(tfNome);
        painelUsuarioDadosUsuarioField.add(tfCpf);
        painelUsuarioDadosUsuarioField.add(tfTelefone);
        painelUsuarioDadosUsuarioField.add(tfEmail);
        painelUsuarioDadosUsuarioField.add(tfSenha);

        formulario.add(painelUsuarioDadosUsuarioLabel);
        formulario.add(painelUsuarioDadosUsuarioField);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica para salvar as alterações
            String nome = tfNome.getText();
            String cpf = tfCpf.getText();
            String telefone = tfTelefone.getText();
            String email = tfEmail.getText();
            String senha = tfSenha.getText();

            if (validarDadosCadastro(nome, cpf, telefone, email, senha)) {
                switch (tipoUsuario) {
                    case "Clientes":
                        editarCliente(selectedIndex, nome, cpf, telefone, email, senha);
                        break;
                    case "Gerentes":
                        editarGerente(selectedIndex, nome, cpf, telefone, email, senha);
                        break;
                    case "Caixas":
                        editarCaixa(selectedIndex, nome, cpf, telefone, email, senha);
                        break;
                }
                dialog.dispose(); // Fecha o diálogo após salvar
            } else {
                JOptionPane.showMessageDialog(dialog, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);

        dialog.add(formulario, BorderLayout.CENTER);
        dialog.add(painelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public void adicionarCliente(String nome, String cpf, String telefone, String email, String senha) {
        try {
            Cliente novoCliente = new Cliente(nome, new Cpf(cpf), new Telefone(telefone), new Email(email), senha);
            DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlCliente.getModel();
            model.addElement(novoCliente);
            JOptionPane.showMessageDialog(tela, "Cliente adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmailException | TelefoneException | CpfException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void adicionarGerente(String nome, String cpf, String telefone, String email, String senha) {
        try {
            Gerente novoGerente = new Gerente(nome, new Cpf(cpf), new Telefone(telefone), new Email(email), senha);
            DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlGerente.getModel();
            model.addElement(novoGerente);
            JOptionPane.showMessageDialog(tela, "Gerente adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmailException | TelefoneException | CpfException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void adicionarCaixa(String nome, String cpf, String telefone, String email, String senha) {
        try {
            Caixa novoCaixa = new Caixa(nome, new Cpf(cpf), new Telefone(telefone), new Email(email), senha);
            DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixa.getModel();
            model.addElement(novoCaixa);
            JOptionPane.showMessageDialog(tela, "Caixa adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmailException | TelefoneException | CpfException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarCliente(int selectedIndex, String nome, String cpf, String telefone, String email, String senha) {
        try {
            Cliente clienteEditado = new Cliente(nome, new Cpf(cpf), new Telefone(telefone), new Email(email), senha);
            DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlCliente.getModel();
            model.set(selectedIndex, clienteEditado);
            JOptionPane.showMessageDialog(tela, "Cliente editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmailException | TelefoneException | CpfException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarGerente(int selectedIndex, String nome, String cpf, String telefone, String email, String senha) {
        try {
            Gerente gerenteEditado = new Gerente(nome, new Cpf(cpf), new Telefone(telefone), new Email(email), senha);
            DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlGerente.getModel();
            model.set(selectedIndex, gerenteEditado);
            JOptionPane.showMessageDialog(tela, "Gerente editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmailException | TelefoneException | CpfException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarCaixa(int selectedIndex, String nome, String cpf, String telefone, String email, String senha) {
        try {
            Caixa caixaEditado = new Caixa(nome, new Cpf(cpf), new Telefone(telefone), new Email(email), senha);
            DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixa.getModel();
            model.set(selectedIndex, caixaEditado);
            JOptionPane.showMessageDialog(tela, "Caixa editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EmailException | TelefoneException | CpfException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    public class EditarUsuario implements ActionListener {
        private TelaAdministrador telaAdministrador;
        private String tipoUsuario;

        public EditarUsuario(TelaAdministrador telaAdministrador, String tipoUsuario) {
            this.telaAdministrador = telaAdministrador;
            this.tipoUsuario = tipoUsuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = -1;

            // Obtém o índice do usuário selecionado na lista
            switch (tipoUsuario) {
                case "Clientes":
                    selectedIndex = telaAdministrador.jlCliente.getSelectedIndex();
                    break;
                case "Gerentes":
                    selectedIndex = telaAdministrador.jlGerente.getSelectedIndex();
                    break;
                case "Caixas":
                    selectedIndex = telaAdministrador.jlCaixa.getSelectedIndex();
                    break;
            }

            if (selectedIndex != -1) {
                telaAdministrador.exibirFormularioEdicao(tipoUsuario, selectedIndex);
            } else {
                JOptionPane.showMessageDialog(telaAdministrador.tela, "Selecione um usuário para editar!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void atualizarFormularioCliente() {
        int selectedIndex = jlCliente.getSelectedIndex();

        if (selectedIndex != -1) {
            DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlCliente.getModel();
            Cliente cliente = model.get(selectedIndex);

            tfNome.setText(cliente.getNome());
            tfEmail.setText(cliente.getEmail().toString());
            tfTelefone.setText(cliente.getNumeroDeTelefone().toString());
            tfCpf.setText(cliente.getCpf().toString());
        }
    }

    public void atualizarFormularioGerente() {
        int selectedIndex = jlGerente.getSelectedIndex();

        if (selectedIndex != -1) {
            DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlGerente.getModel();
            Gerente gerente = model.get(selectedIndex);

            tfNome.setText(gerente.getNome());
            tfEmail.setText(gerente.getEmail().toString());
            tfTelefone.setText(gerente.getNumeroDeTelefone().toString());
            tfCpf.setText(gerente.getCpf().toString());
        }
    }

    public void atualizarFormularioCaixa() {
        int selectedIndex = jlCaixa.getSelectedIndex();

        if (selectedIndex != -1) {
            DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixa.getModel();
            Caixa caixa = model.get(selectedIndex);

            tfNome.setText(caixa.getNome());
            tfEmail.setText(caixa.getEmail().toString());
            tfTelefone.setText(caixa.getNumeroDeTelefone().toString());
            tfCpf.setText(caixa.getCpf().toString());
        }
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

    public List<Gerente> listaGerentes() {
        DefaultListModel<Gerente> modelGerentes = (DefaultListModel<Gerente>) jlGerente.getModel();
        List<Gerente> gerentes = new ArrayList<>();

        for (int i = 0; i < modelGerentes.size(); i++) {
            gerentes.add(modelGerentes.get(i));
        }

        return gerentes;
    }

    public void carregaGerentes(List<Gerente> gerentes) {
        DefaultListModel<Gerente> modelGerente = (DefaultListModel<Gerente>) jlGerente.getModel();
        modelGerente.clear(); // Limpa a lista antes de adicionar os novos registros

        for (Gerente g : gerentes) {
            modelGerente.addElement(g);
        }
    }

    public List<Caixa> listaCaixas() {
        DefaultListModel<Caixa> modelCaixas = (DefaultListModel<Caixa>) jlCaixa.getModel();
        List<Caixa> caixas = new ArrayList<>();

        for (int i = 0; i < modelCaixas.size(); i++) {
            caixas.add(modelCaixas.get(i));
        }

        return caixas;
    }

    public void carregaCaixas(List<Caixa> caixas) {
        DefaultListModel<Caixa> modelCaixa = (DefaultListModel<Caixa>) jlCaixa.getModel();
        modelCaixa.clear(); // Limpa a lista antes de adicionar os novos registros

        for (Caixa c : caixas) {
            modelCaixa.addElement(c);
        }
    }

    public List<Transacao> listaTransacoes() {
        DefaultListModel<Transacao> modelTransacoes = (DefaultListModel<Transacao>) jlTransacoes.getModel();
        List<Transacao> transacoes = new ArrayList<>();

        for (int i = 0; i < modelTransacoes.size(); i++) {
            transacoes.add(modelTransacoes.get(i));
        }

        return transacoes;
    }

    public void carregaTransacoes(List<Transacao> transacoes) {
        DefaultListModel<Transacao> modelTransacoes = (DefaultListModel<Transacao>) jlTransacoes.getModel();
        modelTransacoes.clear(); // Limpa a lista antes de adicionar os novos registros

        for (Transacao t : transacoes) {
            modelTransacoes.addElement(t);
        }
    }

    public void removerUsuario(String tipoUsuario) {
        switch (tipoUsuario) {
            case "Clientes":
                int selectedIndexCliente = jlCliente.getSelectedIndex();
                if (selectedIndexCliente != -1) {
                    DefaultListModel<Cliente> modelCliente = (DefaultListModel<Cliente>) jlCliente.getModel();
                    modelCliente.remove(selectedIndexCliente);
                }
                break;
            case "Gerentes":
                int selectedIndexGerente = jlGerente.getSelectedIndex();
                if (selectedIndexGerente != -1) {
                    DefaultListModel<Gerente> modelGerente = (DefaultListModel<Gerente>) jlGerente.getModel();
                    modelGerente.remove(selectedIndexGerente);
                }
                break;
            case "Caixas":
                int selectedIndexCaixa = jlCaixa.getSelectedIndex();
                if (selectedIndexCaixa != -1) {
                    DefaultListModel<Caixa> modelCaixa = (DefaultListModel<Caixa>) jlCaixa.getModel();
                    modelCaixa.remove(selectedIndexCaixa);
                }
                break;
        }
    }

    public void exibirTransacoes() {
        // Remove o painel atual
        principal.removeAll();

        JPanel areaTransacoes = new JPanel();
        areaTransacoes.setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Transações"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        painel.setLayout(new BorderLayout());

        painel.add(new JScrollPane(jlTransacoes), BorderLayout.CENTER);

        areaTransacoes.add(painel, BorderLayout.CENTER);

        // Adiciona as transações à janela
        principal.add(areaTransacoes);

        // Atualiza tela
        principal.revalidate();
        principal.repaint();
    }

    private boolean validarDadosCadastro(String nome, String cpf, String telefone, String email, String senha) {
        return !nome.isEmpty() && !cpf.isEmpty() && !telefone.isEmpty() && !email.isEmpty() && !senha.isEmpty();
    }
}