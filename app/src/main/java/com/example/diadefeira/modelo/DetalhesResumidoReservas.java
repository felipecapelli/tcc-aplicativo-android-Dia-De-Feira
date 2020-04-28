package com.example.diadefeira.modelo;

import java.time.LocalDateTime;

public class DetalhesResumidoReservas {
    private Long idCompraReserva;
    private Long idFeira;
    private String nomeFeira;
    private String emailProdutor;
    private String nomeProdutor;
    private String dataReserva;

    public Long getIdCompraReserva() {
        return idCompraReserva;
    }

    public void setIdCompraReserva(Long idCompraReserva) {
        this.idCompraReserva = idCompraReserva;
    }

    public Long getIdFeira() {
        return idFeira;
    }

    public void setIdFeira(Long idFeira) {
        this.idFeira = idFeira;
    }

    public String getNomeFeira() {
        return nomeFeira;
    }

    public void setNomeFeira(String nomeFeira) {
        this.nomeFeira = nomeFeira;
    }

    public String getEmailProdutor() {
        return emailProdutor;
    }

    public void setEmailProdutor(String emailProdutor) {
        this.emailProdutor = emailProdutor;
    }

    public String getNomeProdutor() {
        return nomeProdutor;
    }

    public void setNomeProdutor(String nomeProdutor) {
        this.nomeProdutor = nomeProdutor;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }
}
