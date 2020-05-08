package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.diadefeira.ParticiparDeFeiraActivity;
import com.example.diadefeira.converter.ParticiparDeFeiraConverter;
import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.ParticiparDeFeira;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ParticiparDeFeiraTask extends AsyncTask<Void, Void, String> {
    private DadosToken dadosToken;
    private ParticiparDeFeira participarDeFeira;
    private Context contexto;

    public ParticiparDeFeiraTask(DadosToken dadosToken, ParticiparDeFeira participarDeFeira, Context contexto) {
        this.dadosToken = dadosToken;
        this.participarDeFeira = participarDeFeira;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String respostaObtidaComPost = "";
        String json = ParticiparDeFeiraConverter.converterParaJSON(this.participarDeFeira);
        try {
            URL url = new URL("http://10.0.0.103:8080/produtorProdutoFeira");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", dadosToken.getTipo() + " " + dadosToken.getToken());

            System.out.println("token -----------------------------------"+dadosToken.getToken());
            System.out.println("json -----------------------------------"+json);
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            //return "ok";
            Scanner scanner = new Scanner(connection.getInputStream());

            respostaObtidaComPost = scanner.next().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respostaObtidaComPost;
    }

    @Override
    protected void onPostExecute(String respostaObtidaComPost) {
        Toast.makeText(contexto, "Participação da feira Registrada com Sucesso", Toast.LENGTH_SHORT).show();
        System.out.println("After the scan 2====================================================");
        ParticiparDeFeiraActivity participarDeFeiraActivity = (ParticiparDeFeiraActivity)contexto;
        System.out.println("After the scan 3====================================================");
        participarDeFeiraActivity.finish();
    }
}
