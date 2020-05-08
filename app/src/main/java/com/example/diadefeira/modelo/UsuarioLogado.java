package com.example.diadefeira.modelo;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.ListaReservasActivity;
import com.example.diadefeira.task.ListaComprasReservasTask;

import java.io.Serializable;

public class UsuarioLogado implements Serializable {
    private String email;
    private String nome;
    private String telefone;

    public void chamaListadorDeReservasECompras(String token, TextView textViewNomeCliente, ListView listaReservas, ListView listaCompras, Context contexto){
        ListaComprasReservasTask listaComprasReservasTask = new ListaComprasReservasTask(token, "resumido/"+email, textViewNomeCliente, listaReservas, listaCompras, contexto, UsuarioLogado.this);
        listaComprasReservasTask.execute();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}