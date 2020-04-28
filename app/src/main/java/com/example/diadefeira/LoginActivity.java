package com.example.diadefeira;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diadefeira.modelo.Usuario;
import com.example.diadefeira.task.LoginTask;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;

public class LoginActivity extends AppCompatActivity {

    private Button botaoEnviar;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private TextView textViewSenhaOuEmailIncorreto;
    private Usuario usuario;
    private Intent relatoriosReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoEnviar = (Button) findViewById(R.id.activity_login_botao_enviar);
        editTextEmail = (EditText) findViewById(R.id.activity_login_email);
        editTextSenha = (EditText) findViewById(R.id.activity_login_senha);
        textViewSenhaOuEmailIncorreto = (TextView) findViewById(R.id.activity_login_titulo_email_ou_senha_incorretos);

        relatoriosReservas = new Intent (LoginActivity.this, ListaReservasActivity.class);

        botaoEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(editTextEmail.getText().toString());
                usuario.setSenha(editTextSenha.getText().toString());
                LoginTask loginTask = new LoginTask(textViewSenhaOuEmailIncorreto, editTextEmail, editTextSenha, usuario, relatoriosReservas,  LoginActivity.this);
                loginTask.execute();
            }
        });

    }
}
