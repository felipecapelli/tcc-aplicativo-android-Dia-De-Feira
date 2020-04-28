package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.modelo.DetalhesCompraReservaProdutos;

public class DetalhesCompraReservaAdapter extends BaseAdapter {
    private Context contexto;
    private DetalhesCompraReserva detalhesCompraReserva;

    public DetalhesCompraReservaAdapter(Context contexto, DetalhesCompraReserva detalhesCompraReserva) {
        this.contexto = contexto;
        this.detalhesCompraReserva = detalhesCompraReserva;
    }

    @Override
    public int getCount() {
        return detalhesCompraReserva.getListaDetalhesCompraReservaProdutos().size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesCompraReserva.getListaDetalhesCompraReservaProdutos().get(position);
    }

    @Override
    public long getItemId(int position) {
        return detalhesCompraReserva.getListaDetalhesCompraReservaProdutos().get(position).getIdProduto();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesCompraReservaProdutos detalhesCompraReservaProdutos = detalhesCompraReserva.getListaDetalhesCompraReservaProdutos().get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_detalhes_compras_reservas, parent, false);
        }

        TextView textViewIdProduto = (TextView) view.findViewById(R.id.item_lista_compras_reservas_id_produto);
        textViewIdProduto.setText(String.valueOf(detalhesCompraReservaProdutos.getIdProduto()));

        TextView textViewNomeProduto = (TextView) view.findViewById(R.id.item_lista_compras_reservas_nome_produto);
        textViewNomeProduto.setText(detalhesCompraReservaProdutos.getNomeProduto());

        return view;
    }
}
