package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesProdutorFeira;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdutorFeiraJsonParcer {
    public static List<DetalhesProdutorFeira> parseDados(String content) {
        try {
            JSONArray jsonArrayFeiraEProdutos = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayFeiraEProdutos.getJSONArray(0).toString());
            List<DetalhesProdutorFeira> detalhesFeiraList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesProdutorFeira detalhesProdutorFeiraFeira = new DetalhesProdutorFeira();

                detalhesProdutorFeiraFeira.setId(jsonObject.getLong("id"));
                detalhesProdutorFeiraFeira.setData(jsonObject.getString("data"));
                detalhesProdutorFeiraFeira.setNome(jsonObject.getString("nome"));
                detalhesProdutorFeiraFeira.setEndereco(jsonObject.getString("endereco"));


                detalhesFeiraList.add(detalhesProdutorFeiraFeira);
            }

            return detalhesFeiraList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
