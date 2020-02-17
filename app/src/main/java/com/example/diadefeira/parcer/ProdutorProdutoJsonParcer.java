package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesProdutorProduto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdutorProdutoJsonParcer {
    public static List<DetalhesProdutorProduto> parseDados(String content) {
        try {
            JSONArray jsonArrayFeiraEProdutos = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayFeiraEProdutos.getJSONArray(1).toString());
            List<DetalhesProdutorProduto> detalhesProdutoList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesProdutorProduto detalhesProdutorProduto = new DetalhesProdutorProduto();

                detalhesProdutorProduto.setIdProduto(jsonObject.getLong("idProduto"));
                detalhesProdutorProduto.setNomeProduto(jsonObject.getString("nomeProduto"));

                detalhesProdutoList.add(detalhesProdutorProduto);
            }

            return detalhesProdutoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
