package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.adapter.ListaFeirasAdapter;
import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.parcer.FeiraJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FeirasTask extends AsyncTask <Void, Void, List<DetalhesFeira>>{

    private Context contexto;
    private ListView listViewFeiras;
    private String textoParaPesquisa;


    private String resposta;
    private List<DetalhesFeira> detalhesFeiras;

    public FeirasTask(String textoParaPesquisa, ListView listViewFeiras, Context contexto){
        this.listViewFeiras = listViewFeiras;
        this.contexto = contexto;
        this.textoParaPesquisa = textoParaPesquisa;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected List<DetalhesFeira> doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/feiras/buscaPorEndereco/"+textoParaPesquisa);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            //-----------------------------------------------------------------
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }

            resposta = stringBuilder.toString();

            detalhesFeiras = FeiraJsonParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return detalhesFeiras;
    }

    @Override
    protected void onPostExecute(List<DetalhesFeira> detalhesFeiras) {
        ListaFeirasAdapter listaFeirasAdapter = new ListaFeirasAdapter(contexto, detalhesFeiras);
        listViewFeiras.setAdapter(listaFeirasAdapter);
    }
}
