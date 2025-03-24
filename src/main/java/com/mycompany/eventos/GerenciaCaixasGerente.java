package com.mycompany.eventos;

import com.mycompany.persistences.CaixaPersistence;
import com.mycompany.persistences.Persistence;
import com.mycompany.view.TelaGerente;
import com.mycompany.models.Conta.Caixa;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class GerenciaCaixasGerente implements WindowListener {

    private final TelaGerente tela;

    public GerenciaCaixasGerente(TelaGerente tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Caixa> caixaPersistence = new CaixaPersistence();
        List<Caixa> all = caixaPersistence.findAll();
        tela.carregaCaixas(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Caixa> caixaPersistence = new CaixaPersistence();
        caixaPersistence.save(tela.listaCaixas());
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
