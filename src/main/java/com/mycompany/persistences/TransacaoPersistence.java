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

// Gerente Transacao
public class TransacaoPersistence implements Persistence<Transacao> {

    private static final String PATH = DIRECTORY+ File.separator +"transacoes.json";
    
    @Override
    public void save(List<Transacao> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        ArquivoTransacao.salva(PATH, json);
    }

    @Override
    public List<Transacao> findAll() {
        Gson gson = new Gson();

        String json = ArquivoTransacao.le(PATH);

        List<Transacao> transacoes = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Transacao>>() {}.getType();
            transacoes = gson.fromJson(json, tipoLista);

            if (transacoes == null)
                transacoes = new ArrayList<>();
        }

        return transacoes;
    }
}
