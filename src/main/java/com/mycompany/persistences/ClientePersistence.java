package com.mycompany.persistences;

import com.mycompany.models.ClassesAuxiliares.LocalDateTimeAdapter;
import static com.mycompany.persistences.Persistence.DIRECTORY;
import com.mycompany.models.Conta.Cliente;
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

// Cliente Persistence
public class ClientePersistence implements Persistence<Cliente> {
    
    private static final String PATH = DIRECTORY + File.separator + "clientes.json";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    
    @Override
    public void save(List<Cliente> itens) {
        
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        
        if (!diretorio.exists())
            diretorio.mkdirs();
        
        ArquivoCliente.salva(PATH, json);
    }
    
    @Override
    public List<Cliente> findAll() {
        String json = ArquivoCliente.le(PATH);
        
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
