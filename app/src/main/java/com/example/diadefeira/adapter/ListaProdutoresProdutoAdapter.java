package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesProdutorProduto;

import java.util.List;

public class ListaProdutoresProdutoAdapter extends BaseAdapter {
    private final Context contexto;
    private final List<DetalhesProdutorProduto> detalhesProdutoreProdutos;

    public ListaProdutoresProdutoAdapter(Context contexto, List<DetalhesProdutorProduto> detalhesProdutoreProdutos) {
        this.contexto = contexto;
        this.detalhesProdutoreProdutos = detalhesProdutoreProdutos;
    }

    @Override
    public int getCount() {return detalhesProdutoreProdutos.size();}

    @Override
    public Object getItem(int position) {return detalhesProdutoreProdutos.get(position);}

    @Override
    public long getItemId(int position) {return detalhesProdutoreProdutos.get(position).getIdProduto();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesProdutorProduto detalhesProdutorProduto = detalhesProdutoreProdutos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_produtores_item_lista_produtos, parent, false);
        }



        TextView textViewId = (TextView) view.findViewById(R.id.item_lista_produtores_idProduto_produto);
        textViewId.setText(String.valueOf(detalhesProdutorProduto.getIdProduto()));

        TextView textNomeProduto = (TextView) view.findViewById(R.id.item_lista_produtores_nomeProduto_produto);
        textNomeProduto.setText(detalhesProdutorProduto.getNomeProduto());

        return view;
    }
}
