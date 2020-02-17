package com.example.diadefeira.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diadefeira.R;
import com.example.diadefeira.modelo.DetalhesFeira;

import java.util.List;

public class ListaFeirasAdapter extends BaseAdapter {
    private Context contexto;
    private List<DetalhesFeira> detalhesFeiras;

    public ListaFeirasAdapter(Context contexto, List<DetalhesFeira> detalhesFeiras){
        this.contexto = contexto;
        this.detalhesFeiras = detalhesFeiras;
    }

    @Override
    public int getCount() {return detalhesFeiras.size();}

    @Override
    public Object getItem(int position) {return detalhesFeiras.get(position);}

    @Override
    public long getItemId(int position) {return detalhesFeiras.get(position).getId();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesFeira detalhesFeira = detalhesFeiras.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(contexto);

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_lista_feiras_item_lista, parent, false);
        }

        TextView textViewId = (TextView) view.findViewById(R.id.item_lista_feiras_id);
        textViewId.setText(String.valueOf(detalhesFeira.getId()));

        TextView textViewNome = (TextView) view.findViewById(R.id.item_lista_feiras_nome);
        textViewNome.setText(detalhesFeira.getNome());

        TextView textViewData = (TextView) view.findViewById(R.id.item_lista_feiras_data);
        textViewData.setText(detalhesFeira.getData());

        TextView textViewLocal = (TextView) view.findViewById(R.id.item_lista_feiras_local);
        textViewLocal.setText(detalhesFeira.getEndereco());

        return view;
    }
}
