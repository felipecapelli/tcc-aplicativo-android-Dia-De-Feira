package com.example.diadefeira.parcer;

import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.UsuarioLogado;

import org.json.JSONException;
import org.json.JSONObject;

public class BuscaUsuarioLogadoParcer {
    public static UsuarioLogado parseDados(String content) {
        try {
            JSONObject jsonObjectDadosToken = new JSONObject(content);
            if(!jsonObjectDadosToken.has("nomeSitio")){//se não tem nome do sítio é um cliente
                UsuarioLogado usuarioLogado = new UsuarioLogado();
                coletaDadosUsuario(usuarioLogado, jsonObjectDadosToken);
                return  usuarioLogado;
            }else{              //se não, é um produtor (lembrando que o produtor herda de usuario
                ProdutorLogado produtorLogado = new ProdutorLogado();
                coletaDadosProdutor(produtorLogado, jsonObjectDadosToken);
                return  produtorLogado;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static UsuarioLogado coletaDadosUsuario(UsuarioLogado usuarioLogado, JSONObject jsonObjectDadosToken){
        try {
            usuarioLogado.setEmail(jsonObjectDadosToken.getString("email"));
            usuarioLogado.setNome(jsonObjectDadosToken.getString("nome"));
            usuarioLogado.setTelefone(jsonObjectDadosToken.getString("telefone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuarioLogado;
    }

    private static ProdutorLogado coletaDadosProdutor(ProdutorLogado produtorLogado, JSONObject jsonObjectDadosToken){
        coletaDadosUsuario(produtorLogado, jsonObjectDadosToken);
        try{
            produtorLogado.setNomeSitio(jsonObjectDadosToken.getString("nomeSitio"));
            produtorLogado.setEndereco(jsonObjectDadosToken.getString("endereco"));
            produtorLogado.setEntidadeSocial(jsonObjectDadosToken.getString("entidadeSocial"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
         return produtorLogado;
    }
}
