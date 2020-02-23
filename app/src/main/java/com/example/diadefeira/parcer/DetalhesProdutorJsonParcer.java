package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesFeiraProdutorDetalhado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalhesProdutorJsonParcer {

    public static List<DetalhesFeiraProdutorDetalhado> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<DetalhesFeiraProdutorDetalhado> detalhesProdutorList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesFeiraProdutorDetalhado detalhesProdutor = new DetalhesFeiraProdutorDetalhado();

                detalhesProdutor.setIdProduto(jsonObject.getLong("idProduto"));
                detalhesProdutor.setNomeProduto(jsonObject.getString("nomeProduto"));
                detalhesProdutor.setPrecoProduto(new BigDecimal(jsonObject.getString("preco")));
                detalhesProdutor.setOrganicoProduto(jsonObject.getBoolean("organico"));
                detalhesProdutor.setUnidadeProduto(jsonObject.getString("unidade"));

                detalhesProdutorList.add(detalhesProdutor);
            }

            return detalhesProdutorList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
