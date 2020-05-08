package com.example.diadefeira.converter;

import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.modelo.DetalhesCompraReservaProdutos;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class InserirNovaReservaConverter {
    public static String converterParaJSON(DetalhesCompraReserva detalhesCompraReserva, String[] listaCodigosDosProdutos){
        List<DetalhesCompraReservaProdutos> listaDetalhesCompraReservaProdutos = new ArrayList<DetalhesCompraReservaProdutos>();

        for(String produtoString: listaCodigosDosProdutos) {
            DetalhesCompraReservaProdutos detalhesCompraReservaProdutos = new DetalhesCompraReservaProdutos();
            detalhesCompraReservaProdutos.setIdProduto(Long.parseLong(produtoString));
            listaDetalhesCompraReservaProdutos.add(detalhesCompraReservaProdutos);
        }
        detalhesCompraReserva.setListaDetalhesCompraReservaProdutos(listaDetalhesCompraReservaProdutos);


        JSONStringer js = new JSONStringer();

        try{
            js.object();
            js.key("emailUsuario").value(detalhesCompraReserva.getUsuarioEmail());
            js.key("emailProdutor").value(detalhesCompraReserva.getProdutorEmail());
            js.key("IdFeira").value(detalhesCompraReserva.getIdFeira().toString());
            js.key("produtosDto").array();
            for (DetalhesCompraReservaProdutos detalhesCompraReservaProdutos : detalhesCompraReserva.getListaDetalhesCompraReservaProdutos()) {
                js.object();
                js.key("idProduto").value(detalhesCompraReservaProdutos.getIdProduto());
                js.endObject();
            }
            js.endArray();
            js.endObject();
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        String json = js.toString();

        return json;
    }
}
