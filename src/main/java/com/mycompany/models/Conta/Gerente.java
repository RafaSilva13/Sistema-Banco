package com.mycompany.models.Conta;

import com.mycompany.models.ClassesAuxiliares.*;
import com.mycompany.models.Conta.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Gerente
public class Gerente extends Usuario {
    private List<Usuario> usuarios; 
    private List<RendaFixa> rendasFixas;
    private List<RendaVariavel> rendasVariaveis;
    
    public Gerente(String nome, Cpf cpf, Telefone numeroDeTelefone, Email email, String senha) {
        super(nome, cpf, numeroDeTelefone, email, senha);
        this.rendasFixas = new ArrayList<>();
        this.rendasVariaveis = new ArrayList<>();
    }
    
    // Método para avaliar crédito
    public void avaliarCredito(String cpfCliente, double valor, int prazo, boolean aprovado) {
        for (Usuario u : usuarios) {
            if (u instanceof Cliente && u.getCpf().toString().equals(cpfCliente)) {
                Cliente cliente = (Cliente) u;
                if (aprovado) {
                    cliente.solicitarCredito(valor, prazo);
                    System.out.println("Crédito de R$ " + valor + " aprovado para " + cliente.getNome());
                } else {
                    System.out.println("Crédito de R$ " + valor + " reprovado para " + cliente.getNome());
                }
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }
    
    // Método para cadastrar renda fixa
    public void cadastrarRendaFixa(RendaFixa rendaFixa) {
        rendasFixas.add(rendaFixa);
        System.out.println("Renda fixa cadastrada: " + rendaFixa);
    }
    
    // Método para cadastrar renda variável
    public void cadastrarRendaVariavel(RendaVariavel rendaVariavel) {
        rendasVariaveis.add(rendaVariavel);
        System.out.println("Renda variável cadastrada: " + rendaVariavel);
    }
    
    // Método para criar usuários (Cliente, Caixa, Gerente)
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
    
    // Método para remover usuário
    public void removerUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);
        System.out.println("Usuário removido com sucesso.");
    }
    
    // Método para editar usuário
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

    // Getters para listas de renda fixa e variável
    public List<RendaFixa> getRendasFixas() {
        return rendasFixas;
    }

    public List<RendaVariavel> getRendasVariaveis() {
        return rendasVariaveis;
    }
    
    // SOBRESCREVENDO METODOS
    
    @Override
    public String toString() {
        return "Nome: " + nome + "; CPF: " + cpf + "; Telefone: " + numeroDeTelefone + "; Email: " + email + ";";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Cliente cliente = (Cliente)obj;
        
        return Objects.equals(cpf, cliente.cpf);    
    }
}