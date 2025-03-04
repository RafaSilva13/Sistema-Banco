package com.mycompany.models.ClassesAuxiliares;

import com.mycompany.exceptions.*;
import java.util.Objects;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Cpf
public class Cpf {
    private String cpf;

    public Cpf(String cpf) throws CpfException {
        this.cpf = validarCpf(cpf);
    }

    public static String validarCpf(String cpf) throws CpfException {
        // Remove todos os caracteres que não são numeros
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            throw new CpfException("CPF deve conter 11 digitos.");
        }

        // Verifica se todos os digitos são iguais, o que invalida o CPF
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new CpfException("CPF invalido: todos os digitos sao iguais.");
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        
        int primeiroDigito = 11 - (soma % 11);
        
        if (primeiroDigito > 9) {
            primeiroDigito = 0;
        }
        
        // Calcula o segundo digito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }

        // Verifica se os dígitos verificadores estão corretos
        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito ||
            Character.getNumericValue(cpf.charAt(10)) != segundoDigito) {
            throw new CpfException("CPF invalido: digitos verificadores incorretos.");
        }

        return cpf;
    }

    // Getters
    public String getCpf() {
        return cpf;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cpf outroCpf = (Cpf) obj;
        return Objects.equals(cpf, outroCpf.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return cpf;
    }
}