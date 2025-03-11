package com.mycompany.eventos;

import com.mycompany.view.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class AdicionarCaixa implements ActionListener {
    
    private final TelaLogin tela;

    public AdicionarCaixa(TelaLogin tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.addCaixa();
    }
}
