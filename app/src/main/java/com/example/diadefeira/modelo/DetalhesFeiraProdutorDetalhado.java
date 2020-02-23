package com.example.diadefeira.modelo;

import java.math.BigDecimal;

public class DetalhesFeiraProdutorDetalhado {
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private boolean organicoProduto;
    private String unidadeProduto;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public boolean isOrganicoProduto() {
        return organicoProduto;
    }

    public void setOrganicoProduto(boolean organicoProduto) {
        this.organicoProduto = organicoProduto;
    }

    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }
}
