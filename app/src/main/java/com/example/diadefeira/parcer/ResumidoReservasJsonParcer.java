package com.example.diadefeira.parcer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.modelo.DetalhesResumidoReservas;
import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.UsuarioLogado;

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
    public static List<DetalhesResumidoReservas> parseDados(String content, UsuarioLogado usuarioLogado) {
        try {
            JSONArray jsonArrayReservasECompras = new JSONArray(content);
            JSONArray jsonArray = new JSONArray(jsonArrayReservasECompras.getJSONArray(0).toString());
            List<DetalhesResumidoReservas> detalhesReservasList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DetalhesResumidoReservas detalhesResumidoReservas = new DetalhesResumidoReservas();

                try{
                    ProdutorLogado produtorLogado = (ProdutorLogado) usuarioLogado;
                    coletaDadosEmComumDoUsuarioOuProdutor(jsonObject, detalhesResumidoReservas);
                    coletaDadosExclusivosQuandoForUmProdutor(jsonObject, detalhesResumidoReservas);
                }catch (ClassCastException e){
                    coletaDadosEmComumDoUsuarioOuProdutor(jsonObject, detalhesResumidoReservas);
                    coletaDadosExclusivosQuandoForUmUsuario(jsonObject, detalhesResumidoReservas);
                }

                detalhesReservasList.add(detalhesResumidoReservas);
            }

            return detalhesReservasList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void coletaDadosEmComumDoUsuarioOuProdutor(JSONObject jsonObject, DetalhesResumidoReservas detalhesResumidoReservas) throws JSONException {
        detalhesResumidoReservas.setIdCompraReserva(jsonObject.getLong("idCompraReserva"));
        detalhesResumidoReservas.setIdFeira(jsonObject.getLong("idFeira"));
        detalhesResumidoReservas.setNomeFeira(jsonObject.getString("nomeFeira"));
        detalhesResumidoReservas.setDataReserva(jsonObject.getString("dataReserva"));
    }

    private static void coletaDadosExclusivosQuandoForUmProdutor(JSONObject jsonObject, DetalhesResumidoReservas detalhesResumidoReservas) throws JSONException {
        detalhesResumidoReservas.setEmailCliente(jsonObject.getString("emailCliente"));
        detalhesResumidoReservas.setNomeCliente(jsonObject.getString("nomeCliente"));
    }

    private static void coletaDadosExclusivosQuandoForUmUsuario(JSONObject jsonObject, DetalhesResumidoReservas detalhesResumidoReservas) throws JSONException {
        detalhesResumidoReservas.setEmailProdutor(jsonObject.getString("emailProdutor"));
        detalhesResumidoReservas.setNomeProdutor(jsonObject.getString("nomeProdutor"));
    }

}
