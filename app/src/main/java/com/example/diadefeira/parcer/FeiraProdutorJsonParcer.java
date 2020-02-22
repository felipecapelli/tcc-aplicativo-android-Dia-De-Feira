package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesFeiraProdutor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeiraProdutorJsonParcer {

    public static List<DetalhesFeiraProdutor> parseDados(String content) {
        try {
            JSONArray jsonArrayFeiraEProdutor = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayFeiraEProdutor.getJSONArray(1).toString());
            List<DetalhesFeiraProdutor> detalhesFeiraProdutoresList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesFeiraProdutor detalhesFeiraProdutor = new DetalhesFeiraProdutor();

                detalhesFeiraProdutor.setEmail(jsonObject.getString("produtorEmail"));
                detalhesFeiraProdutor.setNome(jsonObject.getString("produtorNome"));

                detalhesFeiraProdutoresList.add(detalhesFeiraProdutor);
            }

            return detalhesFeiraProdutoresList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
