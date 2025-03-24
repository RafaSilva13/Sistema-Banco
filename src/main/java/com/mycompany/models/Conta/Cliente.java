package com.mycompany.models.Conta;

import com.mycompany.exceptions.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.mycompany.models.ClassesAuxiliares.*;
import com.mycompany.persistences.*;
import java.util.Random;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Cliente
public class Cliente extends Usuario {
    
    private int numeroConta;
    private double saldo;

    private List<Transacao> extrato;
    private List<Investimento> investimentos;
    
    public Cliente(String nome, Cpf cpf, Telefone numeroDeTelefone, Email email, String senha) {
        super(nome, cpf, numeroDeTelefone, email, senha);
        
        this.numeroConta = criaNumeroConta() + id;
        this.saldo = 0.0;
        
        this.extrato = new ArrayList<>();
        this.investimentos = new ArrayList<>();
    }
    
    public void depositar(double valor) {
        saldo += valor;
        extrato.add(new Transacao("Depósito", valor, "Depósito realizado"));
    }
    
    public boolean sacar(double valor) throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }
        
        saldo -= valor;
        extrato.add(new Transacao("Saque", valor, "Saque realizado"));
        
        return true;
    }
    
    public boolean transferir(Cliente contaDestino, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            contaDestino.depositar(valor);
            adicionarTransacaoExtrato(new Transacao("Transferência", valor, "Transferência para " + contaDestino.getNome()));
            contaDestino.adicionarTransacaoExtrato(new Transacao("Transferência", valor, "Transferência recebida de " + this.getNome()));

            return true;
        }

        return false;
    }
    
    public void imprimirExtrato() {
        System.out.println("Extrato de " + this.getNome() + ":");
        for (Transacao transacao : extrato) {
            System.out.println(transacao);
        }
    }
    
    // CRIA UM NUMERO ALEATORIO PARA A CONTA ALEATORIO
    public int criaNumeroConta(){
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
    
    // SETTERS
    
    public void adicionarTransacaoExtrato(Transacao transacao) {
        this.extrato.add(transacao);
    }
    
    // Métodos na classe Cliente para edição de dados
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroDeTelefone(Telefone numeroDeTelefone) {
        this.numeroDeTelefone = numeroDeTelefone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    // GETTERS
    
    public List<Transacao> getExtrato() {
        return extrato;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }
    
    public int retornaCodigoIdentificador(){
        return this.numeroConta;
    }

    // SOBRESCREVENDO METODOS
    
    @Override
    public String toString() {
        return "Numero Conta: " + numeroConta + "; Nome: " + nome + "; CPF: " + cpf + "; Telefone: " + numeroDeTelefone + "; Email: " + email + ";";
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
        
        return Objects.equals(numeroConta, cliente.numeroConta);    
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroConta, saldo);
    }

    //ACOES DA CONTA
    
    // Método na classe Cliente
    public void solicitarCredito(double valor, int prazo) {
        extrato.add(new Transacao("Solicitação de Crédito", valor, "Solicitação de crédito com prazo de " + prazo + " meses"));
        System.out.println("Solicitação de crédito de R$ " + valor + " enviada para análise.");
    }
    
    // Método para investir em renda fixa
    public boolean investirRendaFixa(RendaFixa rendaFixa, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            extrato.add(new Transacao("Investimento em Renda Fixa", valor, "Investimento: " + rendaFixa.getDescricao()));
            investimentos.add(rendaFixa); // Adiciona o investimento à lista

            return true;
        }

        return false;
    }

    // Método para investir em renda variável
    public boolean investirRendaVariavel(RendaVariavel rendaVariavel, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            extrato.add(new Transacao("Investimento em Renda Variável", valor, "Investimento: " + rendaVariavel.getDescricao()));
            investimentos.add(rendaVariavel); // Adiciona o investimento à lista

            return true;
        }

        return false;
    }
    
    // Método para carregar investimentos
    public void carregarInvestimentos(List<Investimento> investimentos) {
        for (Investimento investimento : investimentos) {
            if (investimento.getTipo().equals("RendaFixa")) {
                this.investimentos.add((RendaFixa) investimento);
            } else if (investimento.getTipo().equals("RendaVariavel")) {
                this.investimentos.add((RendaVariavel) investimento);
            }
        }
    }
}

