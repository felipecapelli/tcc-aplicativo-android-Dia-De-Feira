package com.example.diadefeira.modelo;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.ListaReservasActivity;
import com.example.diadefeira.task.ListaComprasReservasTask;

public class ProdutorLogado extends UsuarioLogado{
    private String nomeSitio;
    private String endereco;
    private String entidadeSocial;

    public void chamaListadorDeReservasECompras(String token, TextView textViewNomeCliente, ListView listaReservas, ListView listaCompras, Context contexto){
        ListaComprasReservasTask listaComprasReservasTask = new ListaComprasReservasTask(token, "resumidoProdutor/"+super.getEmail(), textViewNomeCliente, listaReservas, listaCompras, contexto, ProdutorLogado.this);
        listaComprasReservasTask.execute();
    }

    public String getNomeSitio() {
        return nomeSitio;
    }

    public void setNomeSitio(String nomeSitio) {
        this.nomeSitio = nomeSitio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEntidadeSocial() {
        return entidadeSocial;
    }

    public void setEntidadeSocial(String entidadeSocial) {
        this.entidadeSocial = entidadeSocial;
    }
}

