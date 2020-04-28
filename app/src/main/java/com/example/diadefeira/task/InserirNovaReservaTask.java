package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diadefeira.converter.InserirNovaReservaConverter;
import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.modelo.DetalhesCompraReservaProdutos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InserirNovaReservaTask  extends AsyncTask<Void, Void, String> {
    private DetalhesCompraReserva detalhesCompraReserva;
    private String[] listaCodigosDosProdutos;
    private Context contexto;

    public InserirNovaReservaTask(DetalhesCompraReserva detalhesCompraReserva, String[] listaCodigosDosProdutos, Context contexto) {
        this.detalhesCompraReserva = detalhesCompraReserva;
        this.listaCodigosDosProdutos = listaCodigosDosProdutos;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String respostaObtidaComPost = "";
        String json = InserirNovaReservaConverter.converterParaJSON(this.detalhesCompraReserva, this.listaCodigosDosProdutos);

        try {
            URL url = new URL("http://10.0.0.103:8080/CompraReserva");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            respostaObtidaComPost = scanner.next();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respostaObtidaComPost;
    }

    @Override
    protected void onPostExecute(String respostaObtidaComPost) {
        Toast.makeText(contexto, "Reserva Salva Com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
