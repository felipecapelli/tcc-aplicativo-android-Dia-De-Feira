package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.diadefeira.DetalhesDoProdutoActivity;
import com.example.diadefeira.adapter.ListaProdutosProdutoresAdapter;
import com.example.diadefeira.modelo.DetalhesFeiraProduto;
import com.example.diadefeira.modelo.DetalhesProdutoProdutorProdutos;
import com.example.diadefeira.parcer.DetalhesProdutoJsonParcer;
import com.example.diadefeira.parcer.FeiraProdutoJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DetalhesProdutoTask  extends AsyncTask<Void, Void, String> {

    private final Long feiraid;
    private final DetalhesFeiraProduto produto;
    private final ListView listaDetalhes;
    private final Context contexto;
    private String resposta;
    private List<DetalhesProdutoProdutorProdutos> detalhesProdutoProdutorProdutos;

    public DetalhesProdutoTask(Long id, DetalhesFeiraProduto produto, ListView listaDetalhes, DetalhesDoProdutoActivity detalhesDoProdutoActivity) {
        this.feiraid = id;
        this.produto = produto;
        this.listaDetalhes = listaDetalhes;
        this.contexto = detalhesDoProdutoActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/produtorProdutoFeira/produtoresNumaFeiraProdutoEspecifico/"+feiraid+"/"+produto.getId());
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

            detalhesProdutoProdutorProdutos = DetalhesProdutoJsonParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @Override
    protected void onPostExecute(String ok) {
        ListaProdutosProdutoresAdapter listaProdutosProdutoresAdapter = new ListaProdutosProdutoresAdapter(contexto, detalhesProdutoProdutorProdutos);
        listaDetalhes.setAdapter(listaProdutosProdutoresAdapter);
    }
}
