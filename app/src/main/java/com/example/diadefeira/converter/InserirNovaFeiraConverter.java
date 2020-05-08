package com.example.diadefeira.converter;

import com.example.diadefeira.modelo.DetalhesFeira;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class InserirNovaFeiraConverter {
    public static String converterParaJSON(DetalhesFeira detalhesFeira) {
            JSONStringer js = new JSONStringer();

            try{
                js.object();
                js.key("nome").value(detalhesFeira.getNome());
                js.key("endereco").value(detalhesFeira.getEndereco());
                js.key("data").value(detalhesFeira.getData());
                js.endObject();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String json = js.toString();

            return json;

        }
}
