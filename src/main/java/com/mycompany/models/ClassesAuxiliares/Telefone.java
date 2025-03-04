package com.mycompany.models.ClassesAuxiliares;

import com.mycompany.exceptions.*;
import java.util.Objects;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Telefone
public final class Telefone {
    private final int ddd;
    private final String numero;

    // Construtor
    public Telefone(String telefone) throws TelefoneException {
        String numeros = validarTelefone(telefone);

        // Extrair o DDD e o número da string
        this.ddd = Integer.parseInt(numeros.substring(0, 2));
        this.numero = numeros.substring(2);
    }

    // Método estático para validar o telefone
    public static String validarTelefone(String telefone) throws TelefoneException {
        Objects.requireNonNull(telefone, "O telefone nao pode ser nulo.");

        // Remover caracteres não numéricos
        String numeros = telefone.replaceAll("[^0-9]", "");

        // Verificar se o telefone tem o comprimento correto
        if (numeros.length() < 10 || numeros.length() > 11) {
            throw new TelefoneException("O telefone deve ter 10 ou 11 digitos (incluindo DDD).");
        }

        // Verificar se o DDD é válido (entre 11 e 99)
        int ddd = Integer.parseInt(numeros.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            throw new TelefoneException("DDD invalido. Deve estar entre 11 e 99.");
        }

        // Verificar se o número tem 8 ou 9 dígitos
        String numero = numeros.substring(2);
        if (numero.length() != 8 && numero.length() != 9) {
            throw new TelefoneException("O numero do telefone deve ter 8 ou 9 digitos.");
        }

        return numeros;
    }

    // Getters
    public int getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    // Formatar o número para exibição
    public String formatarNumero() {
        if (numero.length() == 8) {
            return String.format("(%d) %s-%s", ddd, numero.substring(0, 4), numero.substring(4));
        } else {
            return String.format("(%d) %s-%s", ddd, numero.substring(0, 5), numero.substring(5));
        }
    }

    // Sobrescrita do método toString
    @Override
    public String toString() {
        return formatarNumero();
    }

    // Sobrescrita do método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Telefone otherTelefone = (Telefone) obj;
        return ddd == otherTelefone.ddd && Objects.equals(numero, otherTelefone.numero);
    }

    // Sobrescrita do método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(ddd, numero);
    }
}