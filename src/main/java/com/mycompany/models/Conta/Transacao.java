package com.mycompany.models.Conta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Transacao
public class Transacao {
    private LocalDateTime dataHora;
    private String tipo;
    private double valor;
    private String descricao;

    public Transacao(String tipo, double valor, String descricao) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String toString() {
        return "[" + dataHora + "] " + tipo + ": R$ " + valor + " - " + descricao;
    }

    public String getDataHora() {
        // Formata a data e hora
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}

