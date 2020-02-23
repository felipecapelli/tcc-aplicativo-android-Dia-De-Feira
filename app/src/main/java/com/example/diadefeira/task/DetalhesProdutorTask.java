package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.diadefeira.adapter.ListaProdutosProdutosAdapter;
import com.example.diadefeira.modelo.DetalhesFeiraProdutorDetalhado;
import com.example.diadefeira.parcer.DetalhesProdutorJsonParcer;
import com.example.diadefeira.parcer.FeiraProdutoJsonParcer;
import com.example.diadefeira.parcer.FeiraProdutorJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class DetalhesProdutorTask  extends AsyncTask<Void, Void, String> {
    private final String emailProdutor;
    private final Long idFeira;
    private final ListView listViewDetalhes;
    private final Context contexto;
    private String resposta;

    private List<DetalhesFeiraProdutorDetalhado> detalhesProdutoProdutorProdutor;

    public DetalhesProdutorTask(String email, Long id, ListView listViewDetalhes, Context detalhesDoProdutorActivity) {
        this.emailProdutor = email;
        this.idFeira = id;
        this.listViewDetalhes = listViewDetalhes;
        this.contexto = detalhesDoProdutorActivity;
    }


    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/produtorProdutoFeira/porProdutorPorFeira/"+emailProdutor+"/"+idFeira);
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

            detalhesProdutoProdutorProdutor = DetalhesProdutorJsonParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @Override
    protected void onPostExecute(String ok) {
        ListaProdutosProdutosAdapter listaProdutoresAdapter = new ListaProdutosProdutosAdapter(contexto, detalhesProdutoProdutorProdutor);
        listViewDetalhes.setAdapter(listaProdutoresAdapter);
    }
}
