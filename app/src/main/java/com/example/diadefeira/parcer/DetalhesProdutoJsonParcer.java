package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesProdutoProdutorProdutos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalhesProdutoJsonParcer {
    public static List<DetalhesProdutoProdutorProdutos> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<DetalhesProdutoProdutorProdutos> detalhesProdutoList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesProdutoProdutorProdutos detalhesProduto = new DetalhesProdutoProdutorProdutos();

                detalhesProduto.setEmail(jsonObject.getString("email"));
                detalhesProduto.setNomeProdutor(jsonObject.getString("nomeProdutor"));
                detalhesProduto.setOrganico(jsonObject.getBoolean("organico"));
                detalhesProduto.setUnidade(jsonObject.getString("unidade"));
                detalhesProduto.setPreco(new BigDecimal(jsonObject.getString("preco")));

                detalhesProdutoList.add(detalhesProduto);
            }

            return detalhesProdutoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
