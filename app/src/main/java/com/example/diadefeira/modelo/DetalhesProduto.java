package com.example.diadefeira.modelo;

public class DetalhesProduto {
    private String emailProdutor;
    private String nomeProdutor;
    private Long idFeira;
    private String nomeFeira;
    private String enderecoFeira;

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

    public String getEnderecoFeira() {
        return enderecoFeira;
    }

    public void setEnderecoFeira(String enderecoFeira) {
        this.enderecoFeira = enderecoFeira;
    }
}
