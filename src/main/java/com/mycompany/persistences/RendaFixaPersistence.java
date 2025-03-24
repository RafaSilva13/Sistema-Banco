package com.mycompany.persistences;

import com.mycompany.models.Conta.RendaFixa;
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

public class RendaFixaPersistence implements Persistence<RendaFixa> {

    private static final String PATH = DIRECTORY + File.separator + "rendasFixas.json";

    @Override
    public void save(List<RendaFixa> itens) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<RendaFixa> findAll() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        String json = Arquivo.le(PATH);

        List<RendaFixa> rendasFixas = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<RendaFixa>>() {}.getType();
            rendasFixas = gson.fromJson(json, tipoLista);

            if (rendasFixas == null)
                rendasFixas = new ArrayList<>();
        }

        return rendasFixas;
    }
}