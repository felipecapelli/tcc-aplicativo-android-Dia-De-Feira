package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesFeiraProdutor;

import java.util.List;

public class ListaFeirasProdutoresAdapter extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesFeiraProdutor> detalhesFeirasProdutores;

    public ListaFeirasProdutoresAdapter(Context contexto, List<DetalhesFeiraProdutor> detalhesFeirasProdutores) {
        this.contexto = contexto;
        this.detalhesFeirasProdutores = detalhesFeirasProdutores;
    }

    @Override
    public int getCount() {
        return detalhesFeirasProdutores.size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesFeirasProdutores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; //nesse caso o id é uma String - Esse método não retorna nada
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesFeiraProdutor detalhesProdutorFeira = detalhesFeirasProdutores.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_detalhes_da_feira_item_lista_produtores, parent, false);
        }

        TextView textViewEmail = (TextView) view.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_email_produtor);
        textViewEmail.setText(detalhesProdutorFeira.getEmail());

        TextView textViewNome = (TextView) view.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_nome_produtor);
        textViewNome.setText(detalhesProdutorFeira.getNome());

        return view;
    }
}
