package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesResumidoCompras;

import java.util.List;

public class ListaResumidoComprasAdapter  extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesResumidoCompras> detalhesResumidoCompras;

    public ListaResumidoComprasAdapter(Context contexto, List<DetalhesResumidoCompras> detalhesResumidoCompras) {
        this.contexto = contexto;
        this.detalhesResumidoCompras = detalhesResumidoCompras;
    }

    @Override
    public int getCount() {
        return detalhesResumidoCompras.size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesResumidoCompras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return detalhesResumidoCompras.get(position).getIdCompraReserva();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesResumidoCompras compraResumida = detalhesResumidoCompras.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_compras_reservas_item_lista_compras, parent, false);
        }

        TextView textViewIdCompraReserva = (TextView) view.findViewById(R.id.item_lista_compras_id_compra_reserva);
        textViewIdCompraReserva.setText((String.valueOf(compraResumida.getIdCompraReserva())));

        TextView textViewIdFeira = (TextView) view.findViewById(R.id.item_lista_compras_id_feira);
        textViewIdFeira.setText((String.valueOf(compraResumida.getIdFeira())));

        TextView textViewNomeFeira = (TextView) view.findViewById(R.id.item_lista_compras_nome_feira);
        textViewNomeFeira.setText(compraResumida.getNomeFeira());

        TextView textViewEmailProdutor = (TextView) view.findViewById(R.id.item_lista_compras_email_produtor);
        textViewEmailProdutor.setText(compraResumida.getEmailProdutor());

        TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_compras_nome_produtor);
        textViewNomeProdutor.setText(compraResumida.getNomeProdutor());

        TextView textViewDataReserva = (TextView) view.findViewById(R.id.item_lista_compras_data_venda);
        textViewDataReserva.setText(compraResumida.getDataVenda());

        return view;
    }
}
