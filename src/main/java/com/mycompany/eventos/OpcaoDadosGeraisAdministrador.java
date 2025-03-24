package com.mycompany.eventos;

import com.mycompany.view.TelaGerente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class OpcaoDadosGeraisAdministrador implements ActionListener {

    private final TelaGerente telaAdministrador;

    public OpcaoDadosGeraisAdministrador(TelaGerente telaAdministrador) {
        this.telaAdministrador = telaAdministrador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaAdministrador.exibirDadosGerais();
    }
}