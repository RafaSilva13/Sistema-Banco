package com.mycompany.models.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.mycompany.models.ClassesAuxiliares.*;
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
    
    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            extrato.add(new Transacao("Saque", valor, "Saque realizado"));
            return true;
        }
        return false;
    }
    
    public boolean transferir(Cliente destino, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor);
            extrato.add(new Transacao("Transferência", valor, "Transferência para " + destino.getNome()));
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
    
    public double getSaldo() {
        return saldo;
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
    
    public void solicitarCredito(double valor) {
        System.out.println("Solicitacao de crédito de R$ " + valor + " enviada para analise.");
    }
    
    public void investirRendaFixa(RendaFixa investimento, double valor) {
        if (saldo >= valor) {
            
            saldo -= valor;
            
            investimentos.add(investimento);
            
            extrato.add(new Transacao("Investimento em Renda Fixa", valor, "Investimento: " + investimento.getDescricao()));
            
            System.out.println("Investimento realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para investir.");
        }
    }
    
    public void investirRendaVariavel(RendaVariavel investimento, double valor) {
        if (saldo >= valor) {
            
            saldo -= valor;
            
            investimentos.add(investimento);
            
            extrato.add(new Transacao("Investimento em Renda Variável", valor, "Investimento: " + investimento.getDescricao()));
            
            System.out.println("Investimento realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para investir.");
        }
    }
    
    public void listarInvestimentos() {
        
        System.out.println("Investimentos de " + nome + ":");
        
        for (Investimento investimento : investimentos) {
            System.out.println(investimento);
        }
    }
}

