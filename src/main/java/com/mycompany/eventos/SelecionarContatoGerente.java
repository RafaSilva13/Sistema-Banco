package com.mycompany.eventos;

import com.mycompany.view.TelaGerente;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class SelecionarContatoGerente implements ListSelectionListener {

    private final TelaGerente tela;

    public SelecionarContatoGerente(TelaGerente tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizarFormularioGerente();
    }
}
