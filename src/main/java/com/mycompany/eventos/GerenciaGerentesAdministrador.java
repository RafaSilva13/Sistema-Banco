package com.mycompany.eventos;

import com.mycompany.persistences.GerentePersistence;
import com.mycompany.persistences.Persistence;
import com.mycompany.view.TelaAdministrador;
import com.mycompany.models.Conta.Gerente;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class GerenciaGerentesAdministrador implements WindowListener {

    private final TelaAdministrador tela;

    public GerenciaGerentesAdministrador(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Gerente> gerentePersistence = new GerentePersistence();
        List<Gerente> all = gerentePersistence.findAll();
        tela.carregaGerentes(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Gerente> gerentePersistence = new GerentePersistence();
        gerentePersistence.save(tela.listaGerentes());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
