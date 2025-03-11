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

// Gerente Persistence
public class GerentePersistence implements Persistence<Gerente> {

    private static final String PATH = DIRECTORY+ File.separator +"gerentes.json";
    
    @Override
    public void save(List<Gerente> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        ArquivoGerente.salva(PATH, json);
    }

    @Override
    public List<Gerente> findAll() {
        Gson gson = new Gson();

        String json = ArquivoGerente.le(PATH);

        List<Gerente> gerentes = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Gerente>>() {}.getType();
            gerentes = gson.fromJson(json, tipoLista);

            if (gerentes == null)
                gerentes = new ArrayList<>();
        }

        return gerentes;
    }
}
