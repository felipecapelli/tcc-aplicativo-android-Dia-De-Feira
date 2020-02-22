package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesProdutoProdutorProdutos;

import java.util.List;

public class ListaProdutosProdutoresAdapter  extends BaseAdapter{

    private final Context contexto;
    private final List<DetalhesProdutoProdutorProdutos> detalhesProdutoProdutorProdutos;

    public ListaProdutosProdutoresAdapter(Context contexto, List<DetalhesProdutoProdutorProdutos> detalhesProdutoProdutorProdutos) {
        this.contexto = contexto;
        this.detalhesProdutoProdutorProdutos = detalhesProdutoProdutorProdutos;
    }

    @Override
    public int getCount() {
        return detalhesProdutoProdutorProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesProdutoProdutorProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position; //nesse caso o id é uma String - Esse método não retorna nada;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesProdutoProdutorProdutos detalhesProdutos = detalhesProdutoProdutorProdutos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_detalhes_do_produto_item_lista_detalhes, parent, false);
        }

        TextView textViewIdProdutor = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produto_id_produtor);
        textViewIdProdutor.setText(detalhesProdutos.getEmail());

        TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produto_nome_produtor);
        textViewNomeProdutor.setText(detalhesProdutos.getNomeProdutor());

        TextView textViewOrganico = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produto_boolean_organico);
        textViewOrganico.setText(String.valueOf(detalhesProdutos.isOrganico()));

        TextView textViewPreco = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produto_preco);
        textViewPreco.setText(detalhesProdutos.getPreco().toString());

        TextView textViewUnidade = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produto_unidade);
        textViewUnidade.setText(detalhesProdutos.getUnidade());

        return view;
    }
}
