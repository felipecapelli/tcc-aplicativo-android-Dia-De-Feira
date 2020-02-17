package com.example.diadefeira.parcer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.modelo.DetalhesFeira;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeiraJsonParcer {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<DetalhesFeira> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<DetalhesFeira> detalhesFeiraList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesFeira detalhesFeira = new DetalhesFeira();

                detalhesFeira.setId(jsonObject.getLong("id"));
                detalhesFeira.setNome(jsonObject.getString("nome"));
                detalhesFeira.setEndereco(jsonObject.getString("endereco"));
                detalhesFeira.setData(jsonObject.getString("data"));


                detalhesFeiraList.add(detalhesFeira);
            }

            return detalhesFeiraList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
