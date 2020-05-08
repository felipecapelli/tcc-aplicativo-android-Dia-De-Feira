package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesResumidoReservas;
import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.UsuarioLogado;

import java.util.List;

public class ListaResumidoReservasAdapter  extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesResumidoReservas> detalhesResumidoReservas;

    private UsuarioLogado usuarioLogado;

    public ListaResumidoReservasAdapter(Context contexto, List<DetalhesResumidoReservas> detalhesResumidoReservas, UsuarioLogado usuarioLogado) {
        this.contexto = contexto;
        this.detalhesResumidoReservas = detalhesResumidoReservas;
        this.usuarioLogado = usuarioLogado;
    }

    @Override
    public int getCount() {
        return detalhesResumidoReservas.size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesResumidoReservas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return detalhesResumidoReservas.get(position).getIdCompraReserva();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesResumidoReservas reservaResumida = detalhesResumidoReservas.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_compras_reservas_item_lista_reservas, parent, false);
        }

        TextView textViewIdCompraReserva = (TextView) view.findViewById(R.id.item_lista_reservas_id_compra_reserva);
        textViewIdCompraReserva.setText((String.valueOf(reservaResumida.getIdCompraReserva())));

        TextView textViewIdFeira = (TextView) view.findViewById(R.id.item_lista_reservas_id_feira);
        textViewIdFeira.setText((String.valueOf(reservaResumida.getIdFeira())));

        TextView textViewNomeFeira = (TextView) view.findViewById(R.id.item_lista_reservas_nome_feira);
        textViewNomeFeira.setText(reservaResumida.getNomeFeira());

        TextView textViewDataReserva = (TextView) view.findViewById(R.id.item_lista_reservas_data_reserva);
        textViewDataReserva.setText(reservaResumida.getDataReserva());

        try{
            ProdutorLogado produtorLogado = (ProdutorLogado) usuarioLogado;

            TextView textViewEmailProdutor = (TextView) view.findViewById(R.id.item_lista_reservas_email);
            textViewEmailProdutor.setText(reservaResumida.getEmailCliente());

            TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_reservas_nome);
            textViewNomeProdutor.setText(reservaResumida.getNomeCliente());

            TextView textViewTituloUsuarioOuProdutor = view.findViewById(R.id.item_lista_reservas_titulo_produtor_usuario);
            textViewTituloUsuarioOuProdutor.setText("Cliente");
        }catch(ClassCastException e){
            TextView textViewEmailProdutor = (TextView) view.findViewById(R.id.item_lista_reservas_email);
            textViewEmailProdutor.setText(reservaResumida.getEmailProdutor());

            TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_reservas_nome);
            textViewNomeProdutor.setText(reservaResumida.getNomeProdutor());
        }
        return view;
    }
}
