package com.example.diadefeira.converter;

import com.example.diadefeira.modelo.ParticiparDeFeira;

import org.json.JSONException;
import org.json.JSONStringer;

public class ParticiparDeFeiraConverter {
    public static String converterParaJSON(ParticiparDeFeira participarDeFeira) {

        JSONStringer js = new JSONStringer();

        try{
            js.object();
                js.key("emailProdutor").value(participarDeFeira.getEmailProdutor());
                js.key("idFeira").value(participarDeFeira.getIdFeira());
                js.key("listaProdutos").array();
                    for (int i = 0; i < participarDeFeira.getListaProdutos().length; i++){
                        js.object();
                            js.key("idProduto").value(participarDeFeira.getListaProdutos()[i]);
                            js.key("preco").value(participarDeFeira.getListaPrecos()[i]);
                        js.endObject();
                    }
                js.endArray();
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String json = js.toString();

        return json;


    }
}
