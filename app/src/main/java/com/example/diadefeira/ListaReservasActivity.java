package com.example.diadefeira;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.Usuario;
import com.example.diadefeira.task.DetalhesFeiraTask;
import com.example.diadefeira.task.ListaComprasReservasTask;

import org.w3c.dom.Text;

public class ListaReservasActivity extends AppCompatActivity {
    private String emailCliente;
    private String nomeCliente;

    private TextView textViewEmailCliente;
    private TextView textViewNomeCliente;
    private ListView listaReservas;
    private ListView listaCompras;

    private Button botaoNovaReserva;

    private Usuario usuario;
    private DadosToken dadosToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras_reservas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        textViewEmailCliente = findViewById(R.id.item_lista_compras_reservas_id_cliente);
        textViewNomeCliente = findViewById(R.id.item_lista_compras_reservas_nome_cliente);

        listaReservas = findViewById(R.id.activity_lista_compras_reservas_lista_reservas);
        listaCompras = findViewById(R.id.activity_lista_compras_reservas_lista_compras);

        botaoNovaReserva = findViewById(R.id.item_lista_compras_reservas_botao_nova_reserva);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        textViewEmailCliente.setText(usuario.getEmail());
        emailCliente = usuario.getEmail();

        dadosToken = (DadosToken) intent.getSerializableExtra("dadosToken");
        textViewNomeCliente.setText(dadosToken.getTipo());

        //if(!(emailCliente.isEmpty() && nomeCliente.isEmpty())) {
        if(!(emailCliente.isEmpty())) {
            textViewEmailCliente.setText(emailCliente);
            //textViewNomeCliente.setText(nomeCliente);

            ListaComprasReservasTask listaComprasReservasTask = new ListaComprasReservasTask(dadosToken.getToken(), emailCliente, listaReservas, listaCompras, ListaReservasActivity.this);
            listaComprasReservasTask.execute();
        }

        listaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                TextView textViewIdCompraReserva = item.findViewById(R.id.item_lista_reservas_id_compra_reserva);
                Long idCompraReserva = Long.parseLong(textViewIdCompraReserva.getText().toString());

                Intent intentVaiParaDetalhesComprasReservas = new Intent(ListaReservasActivity.this, DetalhesComprasReservas.class);
                intentVaiParaDetalhesComprasReservas.putExtra("idCompraReserva", idCompraReserva);

                startActivity(intentVaiParaDetalhesComprasReservas);
            }
        });

        listaCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                TextView textViewIdCompraReserva = item.findViewById(R.id.item_lista_compras_id_compra_reserva);
                Long idCompraReserva = Long.parseLong(textViewIdCompraReserva.getText().toString());

                Intent intentVaiParaDetalhesComprasReservas = new Intent(ListaReservasActivity.this, DetalhesComprasReservas.class);
                intentVaiParaDetalhesComprasReservas.putExtra("idCompraReserva", idCompraReserva);

                startActivity(intentVaiParaDetalhesComprasReservas);
            }
        });

        botaoNovaReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inserirNovaReserva = new Intent(ListaReservasActivity.this, InserirNovaReservaActivity.class);
                startActivity(inserirNovaReserva);
            }
        });
    }
}
