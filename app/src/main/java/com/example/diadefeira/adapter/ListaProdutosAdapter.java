package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesProduto;

import java.util.List;

public class ListaProdutosAdapter extends BaseAdapter {
    private Context contexto;
    private List<DetalhesProduto> detalhesProdutos;

    public ListaProdutosAdapter(Context contexto, List<DetalhesProduto> detalhesProdutos) {
        this.contexto = contexto;
        this.detalhesProdutos = detalhesProdutos;
    }

    @Override
    public int getCount() {return detalhesProdutos.size();}

    @Override
    public Object getItem(int position) {return detalhesProdutos.get(position);}

    @Override
    public long getItemId(int position) {return detalhesProdutos.get(position).getIdFeira();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesProduto detalhesProduto = detalhesProdutos.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_produtos_item_lista, parent, false);
        }

        TextView textViewEmailProdutor = (TextView) view.findViewById(R.id.item_lista_produtos_email_produtor);
        textViewEmailProdutor.setText(detalhesProduto.getEmailProdutor());

        TextView textViewNomeProdutor = (TextView) view.findViewById(R.id.item_lista_produtos_nome_produtor);
        textViewNomeProdutor.setText(detalhesProduto.getNomeProdutor());

        TextView textViewIdFeira = (TextView) view.findViewById(R.id.item_lista_produtos_id_feira);
        textViewIdFeira.setText(String.valueOf(detalhesProduto.getIdFeira()));

        TextView textViewNomeFeira = (TextView) view.findViewById(R.id.item_lista_produtos_nome_feira);
        textViewNomeFeira.setText(detalhesProduto.getNomeFeira());

        TextView textViewEnderecoFeira = (TextView) view.findViewById(R.id.item_lista_produtos_endereco_feira);
        textViewEnderecoFeira.setText(detalhesProduto.getEnderecoFeira());

        return view;
    }
}
