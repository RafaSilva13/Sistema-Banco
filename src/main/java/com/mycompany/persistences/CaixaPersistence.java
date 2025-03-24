package com.mycompany.persistences;

import com.mycompany.models.Conta.Caixa;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.models.ClassesAuxiliares.LocalDateTimeAdapter;
import java.time.LocalDateTime;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class CaixaPersistence implements Persistence<Caixa> {

    private static final String PATH = DIRECTORY + File.separator + "caixas.json";

    @Override
    public void save(List<Caixa> itens) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Caixa> findAll() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        String json = Arquivo.le(PATH);

        List<Caixa> caixas = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Caixa>>() {}.getType();
            caixas = gson.fromJson(json, tipoLista);

            if (caixas == null)
                caixas = new ArrayList<>();
        }

        return caixas;
    }
}