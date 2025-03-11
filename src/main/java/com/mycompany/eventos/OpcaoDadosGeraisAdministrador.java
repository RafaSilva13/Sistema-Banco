package com.mycompany.eventos;

import com.mycompany.view.TelaAdministrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class OpcaoDadosGeraisAdministrador implements ActionListener {

    private final TelaAdministrador telaAdministrador;

    public OpcaoDadosGeraisAdministrador(TelaAdministrador telaAdministrador) {
        this.telaAdministrador = telaAdministrador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaAdministrador.exibirDadosGerais();
    }
}