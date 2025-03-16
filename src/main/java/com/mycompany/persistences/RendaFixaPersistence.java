package com.mycompany.persistences;

import com.mycompany.models.ClassesAuxiliares.LocalDateTimeAdapter;
import static com.mycompany.persistences.Persistence.DIRECTORY;
import com.mycompany.models.Conta.RendaFixa;
import com.google.gson.reflect.TypeToken;
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

// RendaFixa Persistence
public class RendaFixaPersistence implements Persistence<RendaFixa> {
    
    private static final String PATH = DIRECTORY + File.separator + "rendasFixas.json";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    
    @Override
    public void save(List<RendaFixa> itens) {
        
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        
        if (!diretorio.exists())
            diretorio.mkdirs();
        
        ArquivoRendaFixa.salva(PATH, json);
    }
    
    @Override
    public List<RendaFixa> findAll() {
        String json = ArquivoRendaFixa.le(PATH);
        
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
