package com.mycompany.eventos;

import com.mycompany.view.TelaAdministrador;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class SelecionarContatoCliente implements ListSelectionListener {

    private final TelaAdministrador tela;

    public SelecionarContatoCliente(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizarFormularioCliente();
    }
}
