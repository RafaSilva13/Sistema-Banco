package com.mycompany.eventos;

import com.mycompany.persistences.ClientePersistence;
import com.mycompany.persistences.Persistence;
import com.mycompany.view.TelaAdministrador;
import com.mycompany.models.Conta.Cliente;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class GerenciaClientesAdministrador implements WindowListener {

    private final TelaAdministrador tela;

    public GerenciaClientesAdministrador(TelaAdministrador tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Cliente> clientePersistence = new ClientePersistence();
        List<Cliente> all = clientePersistence.findAll();
        tela.carregaClientes(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Cliente> clientePersistence = new ClientePersistence();
        clientePersistence.save(tela.listaClientes());
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
