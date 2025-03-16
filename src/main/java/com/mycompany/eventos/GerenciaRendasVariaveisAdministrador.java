package com.mycompany.eventos;

import com.mycompany.persistences.RendaVariavelPersistence;
import com.mycompany.persistences.Persistence;
import com.mycompany.models.Conta.RendaVariavel;
import com.mycompany.view.TelaAdministrador;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class GerenciaRendasVariaveisAdministrador implements WindowListener {

    private final TelaAdministrador tela;

    public GerenciaRendasVariaveisAdministrador(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<RendaVariavel> rendaVariavelPersistence = new RendaVariavelPersistence();
        List<RendaVariavel> all = rendaVariavelPersistence.findAll();
        tela.carregaRendasVariaveis(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<RendaVariavel> rendaVariavelPersistence = new RendaVariavelPersistence();
        rendaVariavelPersistence.save(tela.listaRendasVariaveis());
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
