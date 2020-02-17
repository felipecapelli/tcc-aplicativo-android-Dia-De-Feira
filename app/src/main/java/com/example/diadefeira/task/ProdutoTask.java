package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.diadefeira.adapter.ListaFeirasAdapter;
import com.example.diadefeira.adapter.ListaProdutosAdapter;
import com.example.diadefeira.modelo.DetalhesProduto;
import com.example.diadefeira.parcer.ProdutoJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ProdutoTask extends AsyncTask<Void, Void, List<DetalhesProduto>> {
    private String produto;
    private String local;
    private ListView listViewProdutos;
    private Context contexto;

    private String resposta;
    private List<DetalhesProduto> detalhesProdutos;

    public ProdutoTask(String produto, String local, ListView listViewProdutos, Context contexto) {
        this.produto = produto;
        this.local = local;
        this.listViewProdutos = listViewProdutos;
        this.contexto = contexto;
    }

    @Override
    protected List<DetalhesProduto> doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/produtorProdutoFeira/porProduto/"+produto+"/"+local);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                System.out.println("passei aqui 3: "+line+"\n");
                stringBuilder.append(line+"\n");
            }

            resposta = stringBuilder.toString();

            detalhesProdutos = ProdutoJsonParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return detalhesProdutos;
    }

    @Override
    protected void onPostExecute(List<DetalhesProduto> detalhesProdutos) {
        ListaProdutosAdapter listaProdutosAdapter = new ListaProdutosAdapter(contexto, detalhesProdutos);
        listViewProdutos.setAdapter(listaProdutosAdapter);
    }
}
