package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DadosToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DadosTokenJsonParcer {

    public static DadosToken parseDados(String content) {

        DadosToken dadosToken = new DadosToken();

        try {
            JSONObject jsonObjectDadosToken = new JSONObject(content);

            dadosToken.setToken(jsonObjectDadosToken.getString("token"));
            dadosToken.setTipo(jsonObjectDadosToken.getString("tipo"));

            return dadosToken;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
