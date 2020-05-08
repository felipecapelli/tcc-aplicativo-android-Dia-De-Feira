package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesResumidoCompras;
import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.UsuarioLogado;

import java.util.List;

public class ListaResumidoComprasAdapter  extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesResumidoCompras> detalhesResumidoCompras;
    private UsuarioLogado usuarioLogado;

    public ListaResumidoComprasAdapter(Context contexto, List<DetalhesResumidoCompras> detalhesResumidoCompras, UsuarioLogado usuarioLogado) {
        this.contexto = contexto;
        this.detalhesResumidoCompras = detalhesResumidoCompras;
        this.usuarioLogado = usuarioLogado;
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

        TextView textViewDataReserva = (TextView) view.findViewById(R.id.item_lista_compras_data_venda);
        textViewDataReserva.setText(compraResumida.getDataVenda());

        try{
            ProdutorLogado produtorLogado = (ProdutorLogado) usuarioLogado;

            TextView textViewEmailProdutor = (TextView) view.findViewById(R.id.item_lista_compras_email);
            textViewEmailProdutor.setText(compraResumida.getEmailCliente());

            TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_compras_nome);
            textViewNomeProdutor.setText(compraResumida.getNomeCliente());

            TextView textViewTituloUsuarioOuProdutor = view.findViewById(R.id.item_lista_compras_titulo_produtor_usuario);
            textViewTituloUsuarioOuProdutor.setText("Cliente");
        }catch(ClassCastException e){
            TextView textViewEmailProdutor = (TextView) view.findViewById(R.id.item_lista_compras_email);
            textViewEmailProdutor.setText(compraResumida.getEmailProdutor());

            TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_compras_nome);
            textViewNomeProdutor.setText(compraResumida.getNomeProdutor());
        }

        return view;
    }
}
