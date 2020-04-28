package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.ListaReservasActivity;
import com.example.diadefeira.adapter.ListaResumidoComprasAdapter;
import com.example.diadefeira.adapter.ListaResumidoReservasAdapter;
import com.example.diadefeira.modelo.DetalhesResumidoCompras;
import com.example.diadefeira.modelo.DetalhesResumidoReservas;
import com.example.diadefeira.parcer.ResumidoComprasJsonParcer;
import com.example.diadefeira.parcer.ResumidoReservasJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ListaComprasReservasTask  extends AsyncTask<Void, Void, String> {
    private final ListView listViewReservas;
    private final ListView listViewCompras;
    private final Context contexto;

    private final String emailCliente;
    private String token;

    private String resposta;
    private List<DetalhesResumidoReservas> detalhesResumidoReservas;
    private List<DetalhesResumidoCompras> detalhesResumidoCompras;
    public ListaComprasReservasTask(String token, String emailCliente, ListView listViewReservas, ListView listViewCompras, Context contexto) {
        this.token = token;
        this.emailCliente = emailCliente;
        this.listViewReservas = listViewReservas;
        this.listViewCompras = listViewCompras;
        this.contexto = contexto;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/CompraReserva/resumido/"+emailCliente);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer "+token);
            connection.connect();

            //-----------------------------------------------------------------
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }

            resposta = stringBuilder.toString();

            detalhesResumidoReservas = ResumidoReservasJsonParcer.parseDados(resposta);
            detalhesResumidoCompras = ResumidoComprasJsonParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @Override
    protected void onPostExecute(String ok) {
        ListaResumidoReservasAdapter listaReservasAdapter = new ListaResumidoReservasAdapter(contexto, detalhesResumidoReservas);
        listViewReservas.setAdapter(listaReservasAdapter);

        ListaResumidoComprasAdapter listaComprasAdapter = new ListaResumidoComprasAdapter(contexto, detalhesResumidoCompras);
        listViewCompras.setAdapter(listaComprasAdapter);
    }
}