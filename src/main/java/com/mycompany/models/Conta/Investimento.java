package com.mycompany.models.Conta;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Investimento (classe base)
public abstract class Investimento {
    protected String descricao;
    protected String tipo; // Adicionando um campo para identificar o tipo de investimento

    public Investimento(String descricao, String tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return descricao;
    }
}