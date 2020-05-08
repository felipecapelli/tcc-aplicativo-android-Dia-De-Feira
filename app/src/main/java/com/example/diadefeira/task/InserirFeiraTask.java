package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.diadefeira.InserirNovaFeiraActivity;
import com.example.diadefeira.converter.InserirNovaFeiraConverter;
import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.DetalhesFeira;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class InserirFeiraTask   extends AsyncTask<Void, Void, String> {
    private DadosToken dadosToken;
    DetalhesFeira detalhesFeira;
    Context contexto;

    public InserirFeiraTask(DadosToken dadosToken, DetalhesFeira detalhesFeira, Context contexto) {
        this.dadosToken = dadosToken;
        this.detalhesFeira = detalhesFeira;
        this.contexto = contexto;
    }


    @Override
    protected String doInBackground(Void... voids) {
        String respostaObtidaComPost = "";
        String json = InserirNovaFeiraConverter.converterParaJSON(this.detalhesFeira);

        try {
            URL url = new URL("http://10.0.0.103:8080/feiras");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", dadosToken.getTipo()+" "+dadosToken.getToken());
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
    protected void onPostExecute(String s) {
        Toast.makeText(contexto, "Feira Salva Com sucesso!", Toast.LENGTH_SHORT).show();
        InserirNovaFeiraActivity inserirNovaFeiraActivity = (InserirNovaFeiraActivity)contexto;
        inserirNovaFeiraActivity.finish();
    }
}
