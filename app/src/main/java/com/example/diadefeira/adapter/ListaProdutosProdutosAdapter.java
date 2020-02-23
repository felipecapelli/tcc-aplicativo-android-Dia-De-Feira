package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesFeiraProdutorDetalhado;

import java.util.List;

public class ListaProdutosProdutosAdapter   extends BaseAdapter {

    private final Context contexto;
    private final List<DetalhesFeiraProdutorDetalhado> detalhesProdutoProdutorProdutor;

    public ListaProdutosProdutosAdapter(Context contexto, List<DetalhesFeiraProdutorDetalhado> detalhesProdutoProdutorProdutor) {
        this.contexto = contexto;
        this.detalhesProdutoProdutorProdutor = detalhesProdutoProdutorProdutor;
    }

    @Override
    public int getCount() {
        return detalhesProdutoProdutorProdutor.size();
    }

    @Override
    public Object getItem(int position) {
        return detalhesProdutoProdutorProdutor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return detalhesProdutoProdutorProdutor.get(position).getIdProduto();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesFeiraProdutorDetalhado detalhesProdutor = detalhesProdutoProdutorProdutor.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_detalhes_do_produtor_item_lista_detalhes, parent, false);
        }

        TextView textViewIdProduto = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produtor_id_produto);
        textViewIdProduto.setText(detalhesProdutor.getIdProduto().toString());

        TextView textViewNomeProduto = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produtor_nome_produto);
        textViewNomeProduto.setText(detalhesProdutor.getNomeProduto());

        TextView textViewOrganico = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produtor_boolean_organico);
        textViewOrganico.setText(String.valueOf(detalhesProdutor.isOrganicoProduto()));

        TextView textViewPrecoProduto = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produtor_preco);
        textViewPrecoProduto.setText(detalhesProdutor.getPrecoProduto().toString());

        TextView textViewUnidadeProduto = (TextView) view.findViewById(R.id.item_lista_detalhes_do_produtor_unidade);
        textViewUnidadeProduto.setText(detalhesProdutor.getUnidadeProduto());

        return view;
    }
}
