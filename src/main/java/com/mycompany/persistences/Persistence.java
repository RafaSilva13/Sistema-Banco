package com.mycompany.persistences;

import java.util.List;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public interface Persistence<T> {

    String DIRECTORY = "data";
    void save(List<T> itens);
    List<T> findAll();
}