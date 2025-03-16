package com.mycompany.persistences;

import com.mycompany.models.Conta.Gerente;
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

// Gerente Persistence
public class GerentePersistence implements Persistence<Gerente> {
    
    private static final String PATH = DIRECTORY + File.separator + "gerentes.json";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    
    @Override
    public void save(List<Gerente> itens) {
        
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        
        if (!diretorio.exists())
            diretorio.mkdirs();
        
        ArquivoGerente.salva(PATH, json);
    }
    
    @Override
    public List<Gerente> findAll() {
        String json = ArquivoGerente.le(PATH);
        
        List<Gerente> gerentes = new ArrayList<>();
        
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Gerente>>() {}.getType();
            gerentes = gson.fromJson(json, tipoLista);
            
            if (gerentes == null)
                gerentes = new ArrayList<>();
        }
        
        return gerentes;
    }
}