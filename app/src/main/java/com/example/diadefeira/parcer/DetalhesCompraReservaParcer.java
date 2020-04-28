package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.modelo.DetalhesCompraReservaProdutos;
import com.example.diadefeira.modelo.DetalhesProdutoProdutorProdutos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetalhesCompraReservaParcer {
    public static DetalhesCompraReserva parseDados(String content) {

        try {
            JSONObject jsonObjectDetalhesCompraReserva = new JSONObject(content);
            JSONArray jsonArrayDetalhesCompraReservaProdutos = new JSONArray(jsonObjectDetalhesCompraReserva.getJSONArray("listaProdutosDto").toString());

            List<DetalhesCompraReservaProdutos> listaDetalhesCompraReservaProdutos = new ArrayList<>();

            for (int i = 0; i< jsonArrayDetalhesCompraReservaProdutos.length(); i++){

                JSONObject jsonObject = jsonArrayDetalhesCompraReservaProdutos.getJSONObject(i);
                DetalhesCompraReservaProdutos detalhesCompraReservaProdutos = new DetalhesCompraReservaProdutos();

                detalhesCompraReservaProdutos.setIdProduto(jsonObject.getLong("idProduto"));
                detalhesCompraReservaProdutos.setNomeProduto(jsonObject.getString("nomeProduto"));

                listaDetalhesCompraReservaProdutos.add(detalhesCompraReservaProdutos);
            }

            DetalhesCompraReserva detalhesCompraReserva = new DetalhesCompraReserva();
            detalhesCompraReserva.setListaDetalhesCompraReservaProdutos(listaDetalhesCompraReservaProdutos);

            detalhesCompraReserva.setIdCompraReserva(jsonObjectDetalhesCompraReserva.getLong("idCompraReserva"));
            detalhesCompraReserva.setUsuarioEmail(jsonObjectDetalhesCompraReserva.getString("usuarioEmail"));
            detalhesCompraReserva.setUsuarioNome(jsonObjectDetalhesCompraReserva.getString("usuarioNome"));
            detalhesCompraReserva.setProdutorEmail(jsonObjectDetalhesCompraReserva.getString("produtorEmail"));
            detalhesCompraReserva.setProdutorNome(jsonObjectDetalhesCompraReserva.getString("produtorNome"));

            JSONObject jsonObjectDetalhesCompraReservaFeira = new JSONObject(jsonObjectDetalhesCompraReserva.getJSONObject("feira").toString());
            detalhesCompraReserva.setIdFeira(jsonObjectDetalhesCompraReservaFeira.getLong("id"));
            detalhesCompraReserva.setNomeFeira(jsonObjectDetalhesCompraReservaFeira.getString("nome"));
            detalhesCompraReserva.setEnderecoFeira(jsonObjectDetalhesCompraReservaFeira.getString("endereco"));
            detalhesCompraReserva.setDataFeira(jsonObjectDetalhesCompraReservaFeira.getString("data"));

            detalhesCompraReserva.setDataReserv(jsonObjectDetalhesCompraReserva.getString("dataReserva"));
            detalhesCompraReserva.setDataVenda(jsonObjectDetalhesCompraReserva.getString("dataVenda"));


            return detalhesCompraReserva;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
