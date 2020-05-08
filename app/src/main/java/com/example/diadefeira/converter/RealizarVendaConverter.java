package com.example.diadefeira.converter;

import org.json.JSONException;
import org.json.JSONStringer;

public class RealizarVendaConverter {
    public static String converterParaJSON(long idCompraReserva) {
        JSONStringer js = new JSONStringer();

        try {
            js.object();
                js.key("idCompraReserva").value(idCompraReserva);
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String json = js.toString();
        return json;
    }
}
