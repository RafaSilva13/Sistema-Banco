package com.mycompany.models.Conta;

import com.mycompany.exceptions.SaldoInsuficienteException;
import com.mycompany.models.ClassesAuxiliares.*;
import java.util.Objects;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Caixa
public class Caixa extends Usuario {
    public Caixa(String nome, Cpf cpf, Telefone numeroDeTelefone, Email email, String senha) {
        super(nome, cpf, numeroDeTelefone, email, senha);
    }
    
    public void processarDeposito(Cliente cliente, double valor) {
        cliente.depositar(valor);
        System.out.println("Depósito realizado com sucesso.");
    }
    
    public boolean processarSaque(Cliente cliente, double valor) throws SaldoInsuficienteException {
        if (cliente.sacar(valor)) {
            System.out.println("Saque realizado com sucesso.");
            return true;
        }
        System.out.println("Saldo insuficiente.");
        return false;
    }
    
    public boolean processarTransferencia(Cliente origem, Cliente destino, double valor) {
        if (origem.transferir(destino, valor)) {
            System.out.println("Transferência realizada com sucesso.");
            return true;
        }
        
        System.out.println("Saldo insuficiente.");
        return false;
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