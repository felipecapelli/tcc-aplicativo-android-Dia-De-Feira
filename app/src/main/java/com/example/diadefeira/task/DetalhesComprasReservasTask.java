package com.example.diadefeira.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.DetalhesComprasReservas;
import com.example.diadefeira.adapter.DetalhesCompraReservaAdapter;
import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.parcer.DetalhesCompraReservaParcer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DetalhesComprasReservasTask extends AsyncTask<Void, Void, DetalhesCompraReserva> {
    private DadosToken dadosToken;
    private Long idCompraReserva;

    private TextView textViewIdCompraReserva;
    private TextView textViewUsuarioEmail;
    private EditText editTextUsuarioNome;
    private TextView textViewProdutorEmail;
    private EditText editTextProdutorNome;
    private TextView textViewIdFeira;
    private EditText editTextNomeFeira;
    private EditText editTextEnderecoFeira;
    private EditText editTextDataFeira;
    private EditText editTextDataReserv;
    private EditText editTextDataVenda;
    private TextView textViewDataVendaTitulo;
    private Context contexto;
    private ListView listViewListaDeProdutos;

    private String resposta;
    private DetalhesCompraReserva detalhesCompraReserva;

    public DetalhesComprasReservasTask(DadosToken dadosToken, Long idCompraReserva, TextView textViewIdCompraReserva, TextView textViewUsuarioEmail,
                                       EditText editTextUsuarioNome, TextView textViewProdutorEmail, EditText editTextProdutorNome,
                                       TextView textViewIdFeira, EditText editTextNomeFeira, EditText editTextEnderecoFeira,
                                       EditText editTextDataFeira, EditText editTextDataReserva, EditText editTextDataVenda,
                                       TextView textViewDataVendaTitulo, ListView listViewListaDeProdutos, Context contexto) {
        this.dadosToken = dadosToken;
        this.idCompraReserva = idCompraReserva;
        this.textViewIdCompraReserva = textViewIdCompraReserva;
        this.textViewUsuarioEmail = textViewUsuarioEmail;
        this.editTextUsuarioNome = editTextUsuarioNome;
        this.textViewProdutorEmail = textViewProdutorEmail;
        this.editTextProdutorNome = editTextProdutorNome;
        this.textViewIdFeira = textViewIdFeira;
        this.editTextNomeFeira = editTextNomeFeira;
        this.editTextEnderecoFeira = editTextEnderecoFeira;
        this.editTextDataFeira = editTextDataFeira;
        this.editTextDataReserv = editTextDataReserva;
        this.editTextDataVenda = editTextDataVenda;
        this.textViewDataVendaTitulo  = textViewDataVendaTitulo;

        this.listViewListaDeProdutos = listViewListaDeProdutos;
        this.contexto = contexto;
    }

    @Override
    protected DetalhesCompraReserva doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.0.0.103:8080/CompraReserva/detalhado/"+idCompraReserva);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", dadosToken.getTipo()+" "+dadosToken.getToken());
            connection.connect();

            //-----------------------------------------------------------------
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }

            resposta = stringBuilder.toString();

            detalhesCompraReserva = DetalhesCompraReservaParcer.parseDados(resposta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return detalhesCompraReserva;
    }

    @Override
    protected void onPostExecute(DetalhesCompraReserva detalhesCompraReserva) {
        DetalhesCompraReservaAdapter detalhesCompraReservaAdapter = new DetalhesCompraReservaAdapter(contexto, detalhesCompraReserva);
        listViewListaDeProdutos.setAdapter(detalhesCompraReservaAdapter);

        textViewIdCompraReserva.setText(String.valueOf(detalhesCompraReserva.getIdCompraReserva()));
        textViewUsuarioEmail.setText(detalhesCompraReserva.getUsuarioEmail());
        editTextUsuarioNome.setText(detalhesCompraReserva.getUsuarioNome());
        textViewProdutorEmail.setText(detalhesCompraReserva.getProdutorEmail());
        editTextProdutorNome.setText(detalhesCompraReserva.getProdutorNome());
        textViewIdFeira.setText(String.valueOf(detalhesCompraReserva.getIdFeira()));
        editTextNomeFeira.setText(detalhesCompraReserva.getNomeFeira());
        editTextEnderecoFeira.setText(detalhesCompraReserva.getEnderecoFeira());
        editTextDataFeira.setText(detalhesCompraReserva.getDataFeira());
        editTextDataReserv.setText(detalhesCompraReserva.getDataReserv());

        if(detalhesCompraReserva.getDataVenda() != "null") {
            editTextDataVenda.setText(detalhesCompraReserva.getDataVenda());
        }else{ //se for uma reserva esconde a data de venda e o seu rotulo do layout
            editTextDataVenda.setVisibility(View.GONE);
            textViewDataVendaTitulo.setVisibility(View.GONE);
        }
    }
}
