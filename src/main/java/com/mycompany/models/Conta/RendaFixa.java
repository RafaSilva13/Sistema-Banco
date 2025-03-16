package com.mycompany.models.Conta;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Renda Fixa
public class RendaFixa extends Investimento {
    private double taxaRendimento;
    private int prazoMinimo;
    private int prazoMaximo;
    
    public RendaFixa(String descricao, double taxaRendimento, int prazoMinimo, int prazoMaximo) {
        super(descricao);
        this.taxaRendimento = taxaRendimento;
        this.prazoMinimo = prazoMinimo;
        this.prazoMaximo = prazoMaximo;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public int getPrazoMinimo() {
        return prazoMinimo;
    }

    public int getPrazoMaximo() {
        return prazoMaximo;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Renda Fixa - Taxa: " + taxaRendimento + "%, Prazo: " + prazoMinimo + "-" + prazoMaximo + " meses)";
    }
}
