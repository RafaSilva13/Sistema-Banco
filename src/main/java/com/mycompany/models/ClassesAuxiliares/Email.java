package com.mycompany.models.ClassesAuxiliares;

import com.mycompany.exceptions.*;
import java.util.Objects;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Email
public final class Email {
    private final String email;
    private final String login;
    private final String dominio;

    // Construtor principal
    public Email(String email) throws EmailException {
        this.email = validarEmail(email);
    
        // Divide em 2 partes: login e domínio
        String[] partes = this.email.split("@", 2); 
        
        this.login = partes[0];
        this.dominio = partes[1];
    }

    // Método estático para validar o email
    public static String validarEmail(String email) throws EmailException {
        
        // Verifica se o texto passado é nulo
        Objects.requireNonNull(email, "O email nao pode ser nulo.");

        // Verifica o comprimento máximo do email
        if (email.length() > 254) {
            throw new EmailException("O email excede o comprimento maximo de 254 caracteres.");
        }

        // Expressão regular para validar o email em regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        if (!email.matches(emailRegex)) {
            throw new EmailException("O email fornecido e invalido.");
        }

        return email;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getDominio() {
        return dominio;
    }

    // Sobrescrita do método toString
    @Override
    public String toString() {
        return email;
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
        Email otherEmail = (Email) obj;
        return Objects.equals(email, otherEmail.email);
    }

    // Sobrescrita do método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
