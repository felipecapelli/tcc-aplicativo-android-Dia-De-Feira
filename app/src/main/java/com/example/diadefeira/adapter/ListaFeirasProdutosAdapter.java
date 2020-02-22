package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesFeiraProduto;

import java.util.List;

public class ListaFeirasProdutosAdapter extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesFeiraProduto> detalhesFeirasProdutos;

    public ListaFeirasProdutosAdapter(Context contexto, List<DetalhesFeiraProduto> detalhesFeirasProdutos) {
        this.contexto = contexto;
        this.detalhesFeirasProdutos = detalhesFeirasProdutos;
    }

    @Override
    public int getCount() {
        return detalhesFeirasProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesFeirasProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return detalhesFeirasProdutos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesFeiraProduto detalhesFeiraProduto = detalhesFeirasProdutos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_detalhes_da_feira_item_lista_produtos, parent, false);
        }

        TextView textViewId = (TextView) view.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_id_produto);
        textViewId.setText((String.valueOf(detalhesFeiraProduto.getId())));

        TextView textViewNome = (TextView) view.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_nome_produto);
        textViewNome.setText(detalhesFeiraProduto.getNome());

        return view;
    }
}
