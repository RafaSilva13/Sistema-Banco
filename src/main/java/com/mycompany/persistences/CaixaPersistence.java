package com.mycompany.persistences;

import com.mycompany.models.Conta.*;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// CaixaPersistence
public class CaixaPersistence implements Persistence<Caixa> {

    private static final String PATH = DIRECTORY+ File.separator +"caixas.json";
    
    @Override
    public void save(List<Caixa> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        ArquivoCliente.salva(PATH, json);
    }

    @Override
    public List<Caixa> findAll() {
        Gson gson = new Gson();

        String json = ArquivoCliente.le(PATH);

        List<Caixa> caixas = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Caixa>>() { }.getType();
            caixas = gson.fromJson(json, tipoLista);

            if (caixas == null)
                caixas = new ArrayList<>();
        }

        return caixas;
    }
}
