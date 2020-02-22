package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesFeiraProduto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeiraProdutoJsonParcer {
    public static List<DetalhesFeiraProduto> parseDados(String content) {
        try {
            JSONArray jsonArrayFeiraEProdutos = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayFeiraEProdutos.getJSONArray(0).toString());
            List<DetalhesFeiraProduto> detalhesFeiraProdutosList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesFeiraProduto detalhesFeiraProduto = new DetalhesFeiraProduto();

                detalhesFeiraProduto.setId(jsonObject.getLong("idProduto"));
                detalhesFeiraProduto.setNome(jsonObject.getString("nome"));

                detalhesFeiraProdutosList.add(detalhesFeiraProduto);
            }

            return detalhesFeiraProdutosList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
