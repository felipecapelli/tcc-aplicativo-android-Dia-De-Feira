package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.diadefeira.DetalhesDaFeiraActivity;
import com.example.diadefeira.adapter.ListaFeirasProdutoresAdapter;
import com.example.diadefeira.adapter.ListaFeirasProdutosAdapter;
import com.example.diadefeira.modelo.DetalhesFeiraProduto;
import com.example.diadefeira.modelo.DetalhesFeiraProdutor;
import com.example.diadefeira.parcer.FeiraProdutoJsonParcer;
import com.example.diadefeira.parcer.FeiraProdutorJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DetalhesFeiraTask extends AsyncTask<Void, Void, String> {
    private final ListView listViewProdutos;
    private final ListView listViewProdutores;
    private final Context contexto;
    private final Long idFeira;

    private String resposta;
    private List<DetalhesFeiraProduto> detalhesFeirasProdutos;
    private List<DetalhesFeiraProdutor> detalhesFeirasProdutores;

    public DetalhesFeiraTask(Long idFeira, ListView listaProdutos, ListView listaProdutores, Context contexto) {
        this.idFeira = idFeira;
        this.listViewProdutos = listaProdutos;
        this.listViewProdutores = listaProdutores;
        this.contexto = contexto;
    }


    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/produtorProdutoFeira/detalhesDaFeira/"+idFeira);
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

            System.out.println("---------------------------------------------");
            System.out.println(resposta);
            System.out.println("---------------------------------------------");

            detalhesFeirasProdutos = FeiraProdutoJsonParcer.parseDados(resposta);
            detalhesFeirasProdutores = FeiraProdutorJsonParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @Override
    protected void onPostExecute(String ok) {
        ListaFeirasProdutosAdapter listaProdutosAdapter = new ListaFeirasProdutosAdapter(contexto, detalhesFeirasProdutos);
        listViewProdutos.setAdapter(listaProdutosAdapter);

        ListaFeirasProdutoresAdapter listaProdutoresAdapter = new ListaFeirasProdutoresAdapter(contexto, detalhesFeirasProdutores);
        listViewProdutores.setAdapter(listaProdutoresAdapter);
    }
}
