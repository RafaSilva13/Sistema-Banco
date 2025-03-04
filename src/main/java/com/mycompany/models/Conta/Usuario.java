package com.mycompany.models.Conta;

import com.mycompany.models.ClassesAuxiliares.*;
import java.util.Objects;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Classe abstrata Usuario
public abstract class Usuario {
    
    private static int incrementadorId;
    protected int id;
    protected String senha;
    protected String nome;
    protected Cpf cpf;
    protected Telefone numeroDeTelefone;
    protected Email email;
    
    public Usuario(String nome, Cpf cpf, Telefone numeroDeTelefone, Email email, String senha) {
        this.id = incrementadorId++;
        this.cpf = cpf;
        this.senha = senha;
        this.id = id;
    }
    
    public boolean autenticar(Cpf cpf, String senhaDigitada) {
        return this.senha.equals(senhaDigitada) && this.cpf.equals(cpf);
    }
    
    //GETTERS
    public int getId() { 
        return id; 
    }

    public String getNome() {
        return nome;
    }
    
    public Cpf getCpf() {
        return cpf;
    }

    public Telefone getNumeroDeTelefone() {
        return numeroDeTelefone;
    }

    public Email getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Usuario cliente = (Cliente)obj;
        
        return Objects.equals(cpf, cliente.cpf);    
    }

    public int hashCode() {
        return Objects.hash(nome, cpf, numeroDeTelefone, email);
    }
}