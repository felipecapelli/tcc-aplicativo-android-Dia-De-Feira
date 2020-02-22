package com.example.diadefeira.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DetalhesFeira implements Serializable {
    private Long id;
    private String nome;
    private String endereco;
    private String data;

    public DetalhesFeira(){}

    public DetalhesFeira(Long id, String nome, String endereco, String data){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.data = data;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
