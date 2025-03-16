package com.mycompany.eventos;

import com.mycompany.view.TelaAdministrador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class OpcaoRendaFixaAdministrador implements ActionListener {
    
    private TelaAdministrador telaAdministrador;

    public OpcaoRendaFixaAdministrador(TelaAdministrador telaAdministrador) {
        this.telaAdministrador = telaAdministrador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaAdministrador.exibirRendaFixa();
    }
}