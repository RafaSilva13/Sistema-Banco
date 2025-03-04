package com.mycompany.models.Conta;

import com.mycompany.models.ClassesAuxiliares.*;
import com.mycompany.models.Conta.*;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Gerente
public class Gerente extends Usuario {
    private List<Usuario> usuarios; 
    
    public Gerente(String nome, Cpf cpf, Telefone numeroDeTelefone, Email email, String senha) {
        super(nome, cpf, numeroDeTelefone, email, senha);
    }
    
    public void avaliarCredito(Cliente cliente, double valor, boolean aprovado) {
        if (aprovado) {
            System.out.println("Crédito de R$ " + valor + " aprovado para " + cliente.getNome());
        } else {
            System.out.println("Crédito de R$ " + valor + " reprovado para " + cliente.getNome());
        }
    }
    
    public void cadastrarRendaFixa(String descricao, double taxaRendimento, int prazoMinimo, int prazoMaximo) {
        RendaFixa rendaFixa = new RendaFixa(descricao, taxaRendimento, prazoMinimo, prazoMaximo);
        System.out.println("Renda fixa cadastrada: " + rendaFixa);
    }
    
    public void cadastrarRendaVariavel(String descricao, double percentualRisco, double rentabilidadeEsperada) {
        RendaVariavel rendaVariavel = new RendaVariavel(descricao, percentualRisco, rentabilidadeEsperada);
        System.out.println("Renda variável cadastrada: " + rendaVariavel);
    }
    
    public void criarUsuario(String nome, Cpf cpf, Telefone numeroDeTelefone, Email email, String senha, String tipo) {
        switch (tipo.toLowerCase()) {
            case "cliente":
                usuarios.add(new Cliente(nome, cpf, numeroDeTelefone, email, senha));
                break;
            case "caixa":
                usuarios.add(new Caixa(nome, cpf, numeroDeTelefone, email, senha));
                break;
            case "gerente":
                usuarios.add(new Gerente(nome, cpf, numeroDeTelefone, email, senha));
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
        }
        System.out.println("Usuário criado com sucesso.");
    }
    
    public void removerUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);
        System.out.println("Usuário removido com sucesso.");
    }
    
    public void editarUsuario(int id, String novoNome, String novaSenha) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.nome = novoNome;
                u.senha = novaSenha;
                System.out.println("Usuário editado com sucesso.");
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }
}
