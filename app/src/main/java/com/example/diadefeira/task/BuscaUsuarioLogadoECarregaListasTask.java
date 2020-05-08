package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.ListaReservasActivity;
import com.example.diadefeira.adapter.DetalhesCompraReservaAdapter;
import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.Usuario;
import com.example.diadefeira.modelo.UsuarioLogado;
import com.example.diadefeira.parcer.BuscaUsuarioLogadoParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BuscaUsuarioLogadoECarregaListasTask extends AsyncTask<Void, Void, Void> {
    private DadosToken dadosToken;
    private Usuario usuario;
    private TextView textViewNomeCliente;
    private ListView listaReservas;
    private ListView listaCompras;
    private Button botaoNovaReservaVenda;
    private Button botaoNovaFeira;
    private Button botaoParticiparFeira;
    private Context contexto;

    private String resposta;

    private UsuarioLogado usuarioOuProdutorLogado;


    public BuscaUsuarioLogadoECarregaListasTask(DadosToken dadosToken, Usuario usuario, TextView textViewNomeCliente, ListView listaReservas, ListView listaCompras, Button botaoNovaReservaVenda, Button botaoNovaFeira, Button botaoParticiparFeira, Context contexto) {
        this.dadosToken = dadosToken;
        this.usuario = usuario;
        this.textViewNomeCliente = textViewNomeCliente;
        this.listaReservas = listaReservas;
        this.listaCompras = listaCompras;
        this.botaoNovaReservaVenda = botaoNovaReservaVenda;
        this.botaoNovaFeira = botaoNovaFeira;
        this.botaoParticiparFeira = botaoParticiparFeira;
        this.contexto = contexto;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/usuarios/" + usuario.getEmail());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", dadosToken.getTipo() + " " + dadosToken.getToken());
            connection.connect();

            //-----------------------------------------------------------------
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            resposta = stringBuilder.toString();

            usuarioOuProdutorLogado = BuscaUsuarioLogadoParcer.parseDados(resposta);

            usuarioOuProdutorLogado.chamaListadorDeReservasECompras(dadosToken.getToken(), textViewNomeCliente, listaReservas, listaCompras, contexto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        textViewNomeCliente.setText(usuarioOuProdutorLogado.getNome());
        ListaReservasActivity listaReservasActivity = (ListaReservasActivity) contexto;
        listaReservasActivity.setUsuarioOuProdutorLogado(usuarioOuProdutorLogado);
        try{
            ProdutorLogado produtorLogado = (ProdutorLogado) usuarioOuProdutorLogado;
            this.botaoNovaReservaVenda.setVisibility(View.GONE);

            this.botaoNovaFeira.setVisibility(View.VISIBLE);

            this.botaoParticiparFeira.setVisibility(View.VISIBLE);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}
