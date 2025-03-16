package com.mycompany.persistences;

import com.mycompany.models.ClassesAuxiliares.LocalDateTimeAdapter;
import com.google.gson.reflect.TypeToken;
import com.mycompany.models.Conta.Caixa;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

// Caixa Persistence
public class CaixaPersistence implements Persistence<Caixa> {
    
    private static final String PATH = DIRECTORY + File.separator + "caixas.json";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    
    @Override
    public void save(List<Caixa> itens) {
        
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        
        if (!diretorio.exists())
            diretorio.mkdirs();
        
        ArquivoCaixa.salva(PATH, json);
    }
    
    @Override
    public List<Caixa> findAll() {
        String json = ArquivoCaixa.le(PATH);
        
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
