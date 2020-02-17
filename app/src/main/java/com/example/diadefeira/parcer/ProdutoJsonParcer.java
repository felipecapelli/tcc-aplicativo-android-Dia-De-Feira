package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.modelo.DetalhesProduto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdutoJsonParcer {


    public static List<DetalhesProduto> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<DetalhesProduto> detalhesProdutoList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesProduto detalhesProduto = new DetalhesProduto();

                detalhesProduto.setEmailProdutor(jsonObject.getString("emailProdutor"));
                detalhesProduto.setNomeProdutor(jsonObject.getString("nomeProdutor"));
                detalhesProduto.setIdFeira(jsonObject.getLong("idFeira"));
                detalhesProduto.setNomeFeira(jsonObject.getString("nomeFeira"));
                detalhesProduto.setEnderecoFeira(jsonObject.getString("enderecoFeira"));

                detalhesProdutoList.add(detalhesProduto);
            }

            return detalhesProdutoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
