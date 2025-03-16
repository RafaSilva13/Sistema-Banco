package com.mycompany.persistences;

import com.mycompany.models.ClassesAuxiliares.LocalDateTimeAdapter;
import static com.mycompany.persistences.Persistence.DIRECTORY;
import com.mycompany.models.Conta.RendaVariavel;
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

// RendaVariavel Persistence
public class RendaVariavelPersistence implements Persistence<RendaVariavel> {
    
    private static final String PATH = DIRECTORY + File.separator + "rendasVariaveis.json";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    
    @Override
    public void save(List<RendaVariavel> itens) {
        
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        
        if (!diretorio.exists())
            diretorio.mkdirs();
        
        ArquivoRendaVariavel.salva(PATH, json);
    }
    
    @Override
    public List<RendaVariavel> findAll() {
        String json = ArquivoRendaVariavel.le(PATH);
        
        List<RendaVariavel> rendasVariaveis = new ArrayList<>();
        
        if (!json.trim().isEmpty()) {   
            Type tipoLista = new TypeToken<List<RendaVariavel>>() {}.getType();
            rendasVariaveis = gson.fromJson(json, tipoLista);
            
            if (rendasVariaveis == null)
                rendasVariaveis = new ArrayList<>();
        }
        
        return rendasVariaveis;
    }
}
