package com.example.diadefeira.modelo;

import java.util.List;

public class DetalhesCompraReserva {
    private Long IdCompraReserva;
    private String usuarioEmail;
    private String usuarioNome;
    private String produtorEmail;
    private String produtorNome;
    private Long idFeira;
    private String nomeFeira;
    private String enderecoFeira;
    private String dataFeira;
    private String dataReserv;
    private String dataVenda;

    private List<DetalhesCompraReservaProdutos> listaDetalhesCompraReservaProdutos;


    public Long getIdCompraReserva() {
        return IdCompraReserva;
    }

    public void setIdCompraReserva(Long idCompraReserva) {
        IdCompraReserva = idCompraReserva;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getProdutorEmail() {
        return produtorEmail;
    }

    public void setProdutorEmail(String produtorEmail) {
        this.produtorEmail = produtorEmail;
    }

    public String getProdutorNome() {
        return produtorNome;
    }

    public void setProdutorNome(String produtorNome) {
        this.produtorNome = produtorNome;
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

    public String getDataFeira() {
        return dataFeira;
    }

    public void setDataFeira(String dataFeira) {
        this.dataFeira = dataFeira;
    }

    public String getDataReserv() {
        return dataReserv;
    }

    public void setDataReserv(String dataReserv) {
        this.dataReserv = dataReserv;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<DetalhesCompraReservaProdutos> getListaDetalhesCompraReservaProdutos() {
        return listaDetalhesCompraReservaProdutos;
    }

    public void setListaDetalhesCompraReservaProdutos(List<DetalhesCompraReservaProdutos> listaDetalhesCompraReservaProdutos) {
        this.listaDetalhesCompraReservaProdutos = listaDetalhesCompraReservaProdutos;
    }
}
