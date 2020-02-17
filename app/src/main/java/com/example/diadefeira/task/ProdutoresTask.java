package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.diadefeira.ListaProdutoresActivity;
import com.example.diadefeira.adapter.ListaProdutoresFeiraAdapter;
import com.example.diadefeira.adapter.ListaProdutoresProdutoAdapter;
import com.example.diadefeira.modelo.DetalhesProdutorFeira;
import com.example.diadefeira.modelo.DetalhesProdutorProduto;
import com.example.diadefeira.parcer.ProdutorFeiraJsonParcer;
import com.example.diadefeira.parcer.ProdutorProdutoJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ProdutoresTask extends AsyncTask<Void, Void, String> {
    private final String nomeProdutorBusca;
    private final ListView listViewFeiras;
    private final ListView listViewProdutos;
    private final Context contexto;
    private List<DetalhesProdutorFeira> detalhesProdutoreFeiras;
    private List<DetalhesProdutorProduto> detalhesProdutoreProdutos;

    private String resposta;

    public ProdutoresTask(String nomeProdutorBusca, ListView listViewFeiras, ListView listViewProdutos, ListaProdutoresActivity listaProdutoresActivity) {
        this.nomeProdutorBusca  = nomeProdutorBusca;
        this.listViewFeiras = listViewFeiras;
        this.listViewProdutos = listViewProdutos;
        this.contexto = listaProdutoresActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/produtorProdutoFeira/buscaFeirasEProdutosDoProdutor/"+nomeProdutorBusca);
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

            detalhesProdutoreFeiras = ProdutorFeiraJsonParcer.parseDados(resposta);
            detalhesProdutoreProdutos = ProdutorProdutoJsonParcer.parseDados(resposta);
            System.out.println("Teste --------------------------------");
            System.out.println(detalhesProdutoreProdutos.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return detalhesProdutoreFeiras;
        return "ok";
    }

    @Override
    protected void onPostExecute(String ok) {
        ListaProdutoresFeiraAdapter listaFeirasAdapter = new ListaProdutoresFeiraAdapter(contexto, detalhesProdutoreFeiras);
        listViewFeiras.setAdapter(listaFeirasAdapter);

        ListaProdutoresProdutoAdapter listaProdutosAdapter = new ListaProdutoresProdutoAdapter(contexto, detalhesProdutoreProdutos);
        listViewProdutos.setAdapter(listaProdutosAdapter);
    }
}
