package com.mycompany.models.Conta;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Investimento (classe base)
public abstract class Investimento {
    protected String descricao;
    
    public Investimento(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}