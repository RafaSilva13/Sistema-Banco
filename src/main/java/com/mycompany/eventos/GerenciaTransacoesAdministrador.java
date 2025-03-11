package com.mycompany.eventos;

import com.mycompany.persistences.TransacaoPersistence;
import com.mycompany.persistences.Persistence;
import com.mycompany.view.TelaAdministrador;
import com.mycompany.models.Conta.Transacao;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class GerenciaTransacoesAdministrador implements WindowListener {

    private final TelaAdministrador tela;

    public GerenciaTransacoesAdministrador(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Transacao> transacaoPersistence = new TransacaoPersistence();
        List<Transacao> all = transacaoPersistence.findAll();
        tela.carregaTransacoes(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Transacao> transacaoPersistence = new TransacaoPersistence();
        transacaoPersistence.save(tela.listaTransacoes());
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
