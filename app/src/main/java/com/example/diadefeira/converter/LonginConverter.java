package com.example.diadefeira.converter;

import com.example.diadefeira.modelo.Usuario;

import org.json.JSONException;
import org.json.JSONStringer;

public class LonginConverter {

    public static String converterParaJSON(Usuario usuario) {

        JSONStringer js = new JSONStringer();

        try{
            js.object();
                js.key("email").value(usuario.getEmail());
                js.key("senha").value(usuario.getSenha());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String json = js.toString();

        return json;
    }
}
