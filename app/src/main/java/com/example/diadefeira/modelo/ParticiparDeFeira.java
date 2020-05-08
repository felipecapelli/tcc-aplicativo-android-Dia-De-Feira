package com.example.diadefeira.modelo;

import java.io.Serializable;

public class ParticiparDeFeira implements Serializable {
    private String emailProdutor;
    private Long IdFeira;
    private String[] listaProdutos;
    private String[] listaPrecos;

    public String getEmailProdutor() {
        return emailProdutor;
    }

    public void setEmailProdutor(String emailProdutor) {
        this.emailProdutor = emailProdutor;
    }

    public Long getIdFeira() {
        return IdFeira;
    }

    public void setIdFeira(Long idFeira) {
        IdFeira = idFeira;
    }

    public String[] getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(String[] listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String[] getListaPrecos() {
        return listaPrecos;
    }

    public void setListaPrecos(String[] listaPrecos) {
        this.listaPrecos = listaPrecos;
    }
}
