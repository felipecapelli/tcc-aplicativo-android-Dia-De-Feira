package com.example.diadefeira.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diadefeira.converter.LonginConverter;
import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.Usuario;
import com.example.diadefeira.parcer.DadosTokenJsonParcer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class LoginTask extends AsyncTask<Void, Void, String > {
    private TextView textViewSenhaOuEmailIncorreto;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Usuario usuario;
    private Intent relatoriosReservas;
    private Context contexto;


    public LoginTask(TextView textViewSenhaOuEmailIncorreto, EditText editTextEmail, EditText editTextSenha, Usuario usuario, Intent relatoriosReservas, Context contexto) {
        this.textViewSenhaOuEmailIncorreto = textViewSenhaOuEmailIncorreto;
        this.editTextEmail = editTextEmail;
        this.editTextSenha = editTextSenha;
        this.usuario = usuario;
        this.relatoriosReservas = relatoriosReservas;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String respostaObtidaComPost = "";
        String json = LonginConverter.converterParaJSON(this.usuario);

        try {
            URL url = new URL("http://10.0.0.103:8080/autenticacao");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            respostaObtidaComPost = scanner.next();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return respostaObtidaComPost;
    }

    @Override
    protected void onPostExecute(String resposta) {
        if (resposta != null) {
            DadosToken dadosToken = DadosTokenJsonParcer.parseDados(resposta);
            relatoriosReservas.putExtra("usuario", usuario);
            relatoriosReservas.putExtra("dadosToken", dadosToken);

            Toast.makeText(contexto, "Login Efetuado Com sucesso!  "+dadosToken.toString(), Toast.LENGTH_SHORT).show();

            contexto.startActivity(relatoriosReservas);
        }else{
            textViewSenhaOuEmailIncorreto.setVisibility(View.VISIBLE);
        }
    }
}
