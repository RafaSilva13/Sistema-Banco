package com.mycompany.sistema.banco;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Classe Principal
public class SistemaBanco {
    
    
//    private static List<Usuario> usuarios = new ArrayList<>();
//    private static List<RendaFixa> rendasFixas = new ArrayList<>();
//    private static List<RendaVariavel> rendasVariaveis = new ArrayList<>();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public SistemaBanco() throws CpfException, EmailException, TelefoneException {
//        // Usuários iniciais
//        
//            // Cliente 1
//            Cpf cpf1 = new Cpf("123.456.789-12");
//            Telefone telefone1 = new Telefone("99999-9999");
//            Email email1 = new Email("joao@gmail.com");
//            usuarios.add(new Cliente("João", cpf1, telefone1, email1, "1234"));
//
//            // Cliente 2
//            Cpf cpf2 = new Cpf("987.654.321-98");
//            Telefone telefone2 = new Telefone("98888-8888");
//            Email email2 = new Email("maria@gmail.com");
//            usuarios.add(new Cliente("Maria", cpf2, telefone2, email2, "4321"));
//
//            // Caixa 1
//            Cpf cpfCaixa = new Cpf("741.852.963-74");
//            Telefone telefoneCaixa = new Telefone("97777-7777");
//            Email emailCaixa = new Email("carlos@gmail.com");
//            usuarios.add(new Caixa("Carlos", cpfCaixa, telefoneCaixa, emailCaixa, "5678"));
//
//            // Gerente 1
//            Cpf cpfGerente = new Cpf("369.258.147-36");
//            Telefone telefoneGerente = new Telefone("96666-6666");
//            Email emailGerente = new Email("ana@gmail.com");
//            usuarios.add(new Gerente("Ana", cpfGerente, telefoneGerente, emailGerente, "91011"));
//
//            while (true) {
//                System.out.println("1. Login\n2. Sair");
//                int opcao = scanner.nextInt(); scanner.nextLine();
//
//                if (opcao == 1) login();
//                else break;
//            }
//    }
//    
//    private static void login() throws CpfException, EmailException, TelefoneException {
//        System.out.print("CPF: ");
//        String cpf = scanner.nextLine();
//        System.out.print("Senha: ");
//        String senha = scanner.nextLine();
//        
//        for (Usuario u : usuarios) {
//            if (u.autenticar(new Cpf(cpf), senha)) {
//                if (u instanceof Cliente) menuCliente((Cliente) u);
//                else if (u instanceof Caixa) menuCaixa((Caixa) u);
//                else if (u instanceof Gerente) menuGerente((Gerente) u);
//                return;
//            }
//        }
//        System.out.println("Usuario ou senha invalidos.");
//    }
//    
//    private static void menuCliente(Cliente cliente) {
//        while (true) {
//            System.out.println("1. Depositar\n2. Sacar\n3. Transferir\n4. Ver Extrato\n5. Ver Saldo\n6. Solicitar Credito\n7. Investir em Renda Fixa\n8. Investir em Renda Variavel\n9. Listar Investimentos\n10. Sair");
//            int opcao = scanner.nextInt();
//            
//            switch (opcao) {
//                case 1:
//                    System.out.print("Valor: ");
//                    cliente.depositar(scanner.nextDouble());
//                    break;
//                case 2:
//                    System.out.print("Valor: ");
//                    if (!cliente.sacar(scanner.nextDouble()))
//                        System.out.println("Saldo insuficiente.");
//                    break;
//                case 3:
//                    System.out.print("ID do destinatario: ");
//                    int idDestino = scanner.nextInt();
//                    System.out.print("Valor: ");
//                    double valor = scanner.nextDouble();
//                    
//                    Cliente destino = null;
//                    for (Usuario u : usuarios) {
//                        if (u instanceof Cliente && u.getId() == idDestino) {
//                            destino = (Cliente) u;
//                            break;
//                        }
//                    }
//                    
//                    if (destino != null) {
//                        if (!cliente.transferir(destino, valor))
//                            System.out.println("Saldo insuficiente.");
//                    } else {
//                        System.out.println("Conta nao encontrada.");
//                    }
//                    break;
//                case 4:
//                    cliente.imprimirExtrato();
//                    break;
//                case 5:
//                    System.out.println("Saldo: R$ " + cliente.getSaldo());
//                    break;
//                case 6:
//                    System.out.print("Valor do credito: ");
//                    cliente.solicitarCredito(scanner.nextDouble());
//                    break;
//                case 7:
//                    listarRendasFixas();
//                    System.out.print("Escolha o investimento (ID): ");
//                    int idRendaFixa = scanner.nextInt();
//                    System.out.print("Valor: ");
//                    double valorRendaFixa = scanner.nextDouble();
//                    if (idRendaFixa >= 0 && idRendaFixa < rendasFixas.size()) {
//                        cliente.investirRendaFixa(rendasFixas.get(idRendaFixa), valorRendaFixa);
//                    } else {
//                        System.out.println("Investimento invalido.");
//                    }
//                    break;
//                case 8:
//                    listarRendasVariaveis();
//                    System.out.print("Escolha o investimento (ID): ");
//                    int idRendaVariavel = scanner.nextInt();
//                    System.out.print("Valor: ");
//                    double valorRendaVariavel = scanner.nextDouble();
//                    if (idRendaVariavel >= 0 && idRendaVariavel < rendasVariaveis.size()) {
//                        cliente.investirRendaVariavel(rendasVariaveis.get(idRendaVariavel), valorRendaVariavel);
//                    } else {
//                        System.out.println("Investimento invalido.");
//                    }
//                    break;
//                case 9:
//                    cliente.listarInvestimentos();
//                    break;
//                case 10:
//                    return;
//                default:
//                    System.out.println("Opção invalida.");
//            }
//        }
//    }
//    
//    private static void menuCaixa(Caixa caixa) {
//        while (true) {
//            System.out.println("1. Processar Depósito\n2. Processar Saque\n3. Processar Transferência\n4. Sair");
//            int opcao = scanner.nextInt();
//            if (opcao == 4) break;
//        }
//    }
//    
//    private static void menuGerente(Gerente gerente) throws CpfException, EmailException, TelefoneException {
//        while (true) {
//            System.out.println("1. Avaliar Crédito\n2. Cadastrar Renda Fixa\n3. Cadastrar Renda Variável\n4. Criar Usuário\n5. Remover Usuário\n6. Editar Usuário\n7. Sair");
//            int opcao = scanner.nextInt(); scanner.nextLine();
//            
//            switch (opcao) {
//                case 1:
//                    System.out.print("ID do cliente: ");
//                    int idCliente = scanner.nextInt();
//                    System.out.print("Valor do crédito: ");
//                    double valorCredito = scanner.nextDouble();
//                    System.out.print("Aprovar? (true/false): ");
//                    boolean aprovado = scanner.nextBoolean();
//                    
//                    Cliente cliente = null;
//                    for (Usuario u : usuarios) {
//                        if (u instanceof Cliente && u.getId() == idCliente) {
//                            cliente = (Cliente) u;
//                            break;
//                        }
//                    }
//                    
//                    if (cliente != null) {
//                        gerente.avaliarCredito(cliente, valorCredito, aprovado);
//                    } else {
//                        System.out.println("Cliente não encontrado.");
//                    }
//                    break;
//                case 2:
//                    System.out.print("Descrição: ");
//                    String descricaoFixa = scanner.nextLine();
//                    System.out.print("Taxa de Rendimento (%): ");
//                    double taxaRendimento = scanner.nextDouble();
//                    System.out.print("Prazo Mínimo (meses): ");
//                    int prazoMinimo = scanner.nextInt();
//                    System.out.print("Prazo Máximo (meses): ");
//                    int prazoMaximo = scanner.nextInt();
//                    gerente.cadastrarRendaFixa(descricaoFixa, taxaRendimento, prazoMinimo, prazoMaximo);
//                    break;
//                case 3:
//                    System.out.print("Descrição: ");
//                    String descricaoVariavel = scanner.nextLine();
//                    System.out.print("Percentual de Risco (%): ");
//                    double percentualRisco = scanner.nextDouble();
//                    System.out.print("Rentabilidade Esperada (%): ");
//                    double rentabilidadeEsperada = scanner.nextDouble();
//                    gerente.cadastrarRendaVariavel(descricaoVariavel, percentualRisco, rentabilidadeEsperada);
//                    break;
//                case 4:
//                    System.out.print("Nome: ");
//                    String nome = scanner.nextLine();
//                    
//                    System.out.print("CPF: ");
//                    String cpfString = scanner.nextLine();
//                    Cpf cpf = new Cpf(cpfString);
//                    
//                    System.out.print("Telefone: ");
//                    String numeroDeTelefoneString = scanner.nextLine();
//                    Telefone numeroDeTelefone = new Telefone(numeroDeTelefoneString);
//                    
//                    System.out.print("Email: ");
//                    String emailString = scanner.nextLine();
//                    Email email = new Email(emailString);
//                    
//                    System.out.print("Senha: ");
//                    String senha = scanner.nextLine();
//                    System.out.print("Tipo (Cliente/Caixa/Gerente): ");
//                    String tipo = scanner.nextLine();
//                    
//                    gerente.criarUsuario(nome, cpf, numeroDeTelefone, email, senha, tipo);
//                    break;
//                case 5:
//                    System.out.print("ID do usuário: ");
//                    int idRemover = scanner.nextInt();
//                    gerente.removerUsuario(idRemover);
//                    break;
//                case 6:
//                    System.out.print("ID do usuário: ");
//                    int idEditar = scanner.nextInt(); scanner.nextLine();
//                    System.out.print("Novo nome: ");
//                    String novoNome = scanner.nextLine();
//                    System.out.print("Nova senha: ");
//                    String novaSenha = scanner.nextLine();
//                    gerente.editarUsuario(idEditar, novoNome, novaSenha);
//                    break;
//                case 7:
//                    return;
//                default:
//                    System.out.println("Opção inválida.");
//            }
//        }
//    }
//    
//    private static void listarRendasFixas() {
//        System.out.println("Rendas Fixas Disponíveis:");
//        for (int i = 0; i < rendasFixas.size(); i++) {
//            System.out.println(i + ". " + rendasFixas.get(i));
//        }
//    }
//    
//    private static void listarRendasVariaveis() {
//        System.out.println("Rendas Variáveis Disponíveis:");
//        for (int i = 0; i < rendasVariaveis.size(); i++) {
//            System.out.println(i + ". " + rendasVariaveis.get(i));
//        }
//    }
}
