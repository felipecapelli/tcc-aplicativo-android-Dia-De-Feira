package com.example.diadefeira.parcer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.modelo.DetalhesResumidoCompras;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResumidoComprasJsonParcer {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<DetalhesResumidoCompras> parseDados(String content) {
        try {
            JSONArray jsonArrayReservasECompras = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayReservasECompras.getJSONArray(1).toString());
            List<DetalhesResumidoCompras> detalhesComprasList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesResumidoCompras detalhesResumidoCompras = new DetalhesResumidoCompras();

                detalhesResumidoCompras.setIdCompraReserva(jsonObject.getLong("idCompraReserva"));
                detalhesResumidoCompras.setIdFeira(jsonObject.getLong("idFeira"));
                detalhesResumidoCompras.setNomeFeira(jsonObject.getString("nomeFeira"));
                detalhesResumidoCompras.setEmailProdutor(jsonObject.getString("emailProdutor"));
                detalhesResumidoCompras.setNomeProdutor(jsonObject.getString("nomeProdutor"));
                detalhesResumidoCompras.setDataVenda(jsonObject.getString("dataVenda"));

                detalhesComprasList.add(detalhesResumidoCompras);
            }

            return detalhesComprasList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
