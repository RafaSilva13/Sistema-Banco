package com.mycompany.eventos;

import com.mycompany.view.TelaGerente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class RemoverUsuario implements ActionListener {

    private final TelaGerente tela;
    private String tipoUsuario;

    public RemoverUsuario(TelaGerente tela, String tipoUsuario) {
        this.tela = tela;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.removerUsuario(tipoUsuario);
    }
}

