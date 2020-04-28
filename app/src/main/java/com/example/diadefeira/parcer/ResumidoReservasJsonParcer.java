package com.example.diadefeira.parcer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.modelo.DetalhesResumidoReservas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResumidoReservasJsonParcer {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<DetalhesResumidoReservas> parseDados(String content) {
        try {
            JSONArray jsonArrayReservasECompras = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayReservasECompras.getJSONArray(0).toString());
            List<DetalhesResumidoReservas> detalhesReservasList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesResumidoReservas detalhesResumidoReservas = new DetalhesResumidoReservas();

                detalhesResumidoReservas.setIdCompraReserva(jsonObject.getLong("idCompraReserva"));
                detalhesResumidoReservas.setIdFeira(jsonObject.getLong("idFeira"));
                detalhesResumidoReservas.setNomeFeira(jsonObject.getString("nomeFeira"));
                detalhesResumidoReservas.setEmailProdutor(jsonObject.getString("emailProdutor"));
                detalhesResumidoReservas.setNomeProdutor(jsonObject.getString("nomeProdutor"));
                detalhesResumidoReservas.setDataReserva(jsonObject.getString("dataReserva"));

                detalhesReservasList.add(detalhesResumidoReservas);
            }

            return detalhesReservasList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
