package com.mycompany.models.Conta;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Renda Variável
public class RendaVariavel extends Investimento {
    private double percentualRisco;
    private double rentabilidadeEsperada;
    
    public RendaVariavel(String descricao, double percentualRisco, double rentabilidadeEsperada) {
        super(descricao);
        this.percentualRisco = percentualRisco;
        this.rentabilidadeEsperada = rentabilidadeEsperada;
    }

    public double getPercentualRisco() {
        return percentualRisco;
    }

    public double getRentabilidadeEsperada() {
        return rentabilidadeEsperada;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Renda Variável - Risco: " + percentualRisco + "%, Rentabilidade Esperada: " + rentabilidadeEsperada + "%)";
    }
}