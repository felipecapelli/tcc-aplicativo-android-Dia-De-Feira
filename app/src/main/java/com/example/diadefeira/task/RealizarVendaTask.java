package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.diadefeira.DetalhesComprasReservas;
import com.example.diadefeira.converter.RealizarVendaConverter;
import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.DetalhesCompraReserva;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RealizarVendaTask  extends AsyncTask<Void, Void, String> {
    private long idCompraReserva;
    private DadosToken dadosToken;
    private Context contexto;

    public RealizarVendaTask(long idCompraReserva, DadosToken dadosToken, Context contexto) {
        this.idCompraReserva = idCompraReserva;
        this.dadosToken = dadosToken;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String respostaObtidaComPost = "";
        String json = RealizarVendaConverter.converterParaJSON(this.idCompraReserva);

        try {
            URL url = new URL("http://10.0.0.103:8080/CompraReserva");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", dadosToken.getTipo()+" "+dadosToken.getToken());
            connection.setRequestMethod("PUT");

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
    protected void onPostExecute(String s) {
        Toast.makeText(contexto, "Venda registrada", Toast.LENGTH_LONG).show();
        DetalhesComprasReservas detalhesComprasReservas = (DetalhesComprasReservas) contexto;
        detalhesComprasReservas.finish();
    }
}
