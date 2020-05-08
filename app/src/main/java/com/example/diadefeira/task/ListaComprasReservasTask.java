package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.diadefeira.ListaReservasActivity;
import com.example.diadefeira.adapter.ListaResumidoComprasAdapter;
import com.example.diadefeira.adapter.ListaResumidoReservasAdapter;
import com.example.diadefeira.modelo.DetalhesResumidoCompras;
import com.example.diadefeira.modelo.DetalhesResumidoReservas;
import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.UsuarioLogado;
import com.example.diadefeira.parcer.ResumidoComprasJsonParcer;
import com.example.diadefeira.parcer.ResumidoReservasJsonParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ListaComprasReservasTask  extends AsyncTask<Void, Void, String> {
    private final ListView listViewReservas;
    private final ListView listViewCompras;
    private final Context contexto;

    private final String caminhoUsuarioOuProdutor;
    private String token;

    private TextView textViewNomeCliente;

    private String resposta;
    private List<DetalhesResumidoReservas> detalhesResumidoReservas;
    private List<DetalhesResumidoCompras> detalhesResumidoCompras;
    private UsuarioLogado usuarioLogado;
    public ListaComprasReservasTask(String token, String caminhoUsuarioOuProdutor, TextView textViewNomeCliente, ListView listViewReservas, ListView listViewCompras, Context contexto, UsuarioLogado usuarioLogado) {
        this.token = token;
        this.caminhoUsuarioOuProdutor = caminhoUsuarioOuProdutor;
        this.textViewNomeCliente = textViewNomeCliente;
        this.listViewReservas = listViewReservas;
        this.listViewCompras = listViewCompras;
        this.contexto = contexto;
        this.usuarioLogado = usuarioLogado;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/CompraReserva/"+caminhoUsuarioOuProdutor);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer "+token);
            connection.connect();

            //-----------------------------------------------------------------
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }

            resposta = stringBuilder.toString();

            detalhesResumidoReservas = ResumidoReservasJsonParcer.parseDados(resposta, usuarioLogado);
            detalhesResumidoCompras = ResumidoComprasJsonParcer.parseDados(resposta, usuarioLogado);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @Override
    protected void onPostExecute(String ok) {
        ListaResumidoReservasAdapter listaReservasAdapter = new ListaResumidoReservasAdapter(contexto, detalhesResumidoReservas, usuarioLogado);
        listViewReservas.setAdapter(listaReservasAdapter);

        ListaResumidoComprasAdapter listaComprasAdapter = new ListaResumidoComprasAdapter(contexto, detalhesResumidoCompras, usuarioLogado);
        listViewCompras.setAdapter(listaComprasAdapter);
    }
}