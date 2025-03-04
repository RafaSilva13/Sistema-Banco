package com.mycompany.persistences;

import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Persistence
public interface Persistence<T> {

    String DIRECTORY = "data";
    public void save(List<T> itens);
    public List<T> findAll();

}

