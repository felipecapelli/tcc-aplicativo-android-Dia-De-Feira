package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesProdutorFeira;

import java.util.List;

public class ListaProdutoresFeiraAdapter extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesProdutorFeira> detalhesProdutoreFeiras;

    public ListaProdutoresFeiraAdapter(Context contexto, List<DetalhesProdutorFeira> detalhesProdutoreFeiras) {
        this.contexto = contexto;
        this.detalhesProdutoreFeiras = detalhesProdutoreFeiras;
    }

    @Override
    public int getCount() {return detalhesProdutoreFeiras.size();}

    @Override
    public Object getItem(int position) {return detalhesProdutoreFeiras.get(position);}

    @Override
    public long getItemId(int position) {return  detalhesProdutoreFeiras.get(position).getId();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesProdutorFeira detalhesProdutorFeira = detalhesProdutoreFeiras.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_produtores_item_lista_feiras, parent, false);
        }



        TextView textViewId = (TextView) view.findViewById(R.id.item_lista_produtores_id_feira);
        textViewId.setText(String.valueOf(detalhesProdutorFeira.getId()));

        TextView textViewData = (TextView) view.findViewById(R.id.item_lista_produtores_data_feira);
        textViewData.setText(String.valueOf(detalhesProdutorFeira.getData()));

        TextView textViewNome = (TextView) view.findViewById(R.id.item_lista_produtores_nome_feira);
        textViewNome.setText(String.valueOf(detalhesProdutorFeira.getNome()));

        TextView textViewEndereco = (TextView) view.findViewById(R.id.item_lista_produtores_endereco_feira);
        textViewEndereco.setText(String.valueOf(detalhesProdutorFeira.getEndereco()));


        return view;
    }
}
