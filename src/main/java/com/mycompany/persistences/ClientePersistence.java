package com.mycompany.persistences;

import com.mycompany.models.Conta.Cliente;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.mycompany.models.ClassesAuxiliares.LocalDateTimeAdapter;
import com.mycompany.models.ClassesAuxiliares.RuntimeTypeAdapterFactory;
import com.mycompany.models.Conta.*;
import java.time.LocalDateTime;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class ClientePersistence implements Persistence<Cliente> {

    private static final String PATH = DIRECTORY + File.separator + "clientes.json";

    @Override
    public void save(List<Cliente> itens) {
        // Configura o GSON para lidar com a classe abstrata Investimento
        RuntimeTypeAdapterFactory<Investimento> investimentoAdapterFactory = RuntimeTypeAdapterFactory.of(Investimento.class, "tipo").registerSubtype(RendaFixa.class, "RendaFixa").registerSubtype(RendaVariavel.class, "RendaVariavel");

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(investimentoAdapterFactory).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Cliente> findAll() {
        // Configura o GSON para lidar com a classe abstrata Investimento
        RuntimeTypeAdapterFactory<Investimento> investimentoAdapterFactory = RuntimeTypeAdapterFactory.of(Investimento.class, "tipo").registerSubtype(RendaFixa.class, "RendaFixa").registerSubtype(RendaVariavel.class, "RendaVariavel");

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(investimentoAdapterFactory).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

        String json = Arquivo.le(PATH);

        List<Cliente> clientes = new ArrayList<>();
        
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Cliente>>() {}.getType();
            clientes = gson.fromJson(json, tipoLista);

            if (clientes == null)
                clientes = new ArrayList<>();
        }

        return clientes;
    }
}