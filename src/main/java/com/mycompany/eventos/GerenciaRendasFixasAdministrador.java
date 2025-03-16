package com.mycompany.eventos;

import com.mycompany.persistences.RendaFixaPersistence;
import com.mycompany.persistences.Persistence;
import com.mycompany.models.Conta.RendaFixa;
import com.mycompany.view.TelaAdministrador;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class GerenciaRendasFixasAdministrador implements WindowListener {

    private final TelaAdministrador tela;

    public GerenciaRendasFixasAdministrador(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<RendaFixa> rendasFixasPersistence = new RendaFixaPersistence();
        List<RendaFixa> all = rendasFixasPersistence.findAll();
        tela.carregaRendasFixas(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<RendaFixa> rendasFixasPersistence = new RendaFixaPersistence();
        rendasFixasPersistence.save(tela.listaRendasFixas());
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
