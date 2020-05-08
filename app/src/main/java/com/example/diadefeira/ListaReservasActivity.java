package com.example.diadefeira;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.Usuario;
import com.example.diadefeira.modelo.UsuarioLogado;
import com.example.diadefeira.task.BuscaUsuarioLogadoECarregaListasTask;

public class ListaReservasActivity extends AppCompatActivity {
    private TextView textViewEmailCliente;
    private TextView textViewNomeCliente;
    private ListView listaReservas;
    private ListView listaCompras;

    private Button botaoNovaReservaVenda;
    private Button botaoNovaFeira;
    private Button botaoParticiparFeira;

    private Usuario usuario;
    private DadosToken dadosToken;
    private UsuarioLogado usuarioOuProdutorLogado;


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
        botaoNovaReservaVenda = findViewById(R.id.item_lista_compras_reservas_botao_nova_reserva_venda);
        botaoNovaFeira = findViewById(R.id.item_lista_compras_reservas_botao_nova_feira);
        botaoParticiparFeira = findViewById(R.id.item_lista_compras_reservas_botao_participar_feira);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        dadosToken = (DadosToken) intent.getSerializableExtra("dadosToken");
        textViewEmailCliente.setText(usuario.getEmail());


        BuscaUsuarioLogadoECarregaListasTask buscaUsuarioLogadoECarregaListasTask = new BuscaUsuarioLogadoECarregaListasTask(dadosToken, usuario, textViewNomeCliente, listaReservas, listaCompras, botaoNovaReservaVenda, botaoNovaFeira, botaoParticiparFeira,ListaReservasActivity.this);
        buscaUsuarioLogadoECarregaListasTask.execute();


        listaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                TextView textViewIdCompraReserva = item.findViewById(R.id.item_lista_reservas_id_compra_reserva);
                Long idCompraReserva = Long.parseLong(textViewIdCompraReserva.getText().toString());

                Intent intentVaiParaDetalhesComprasReservas = new Intent(ListaReservasActivity.this, DetalhesComprasReservas.class);
                intentVaiParaDetalhesComprasReservas.putExtra("idCompraReserva", idCompraReserva);
                intentVaiParaDetalhesComprasReservas.putExtra("dadosToken", dadosToken);
                intentVaiParaDetalhesComprasReservas.putExtra("usuario", usuario);
                intentVaiParaDetalhesComprasReservas.putExtra("usuarioLogado", usuarioOuProdutorLogado);

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
                intentVaiParaDetalhesComprasReservas.putExtra("dadosToken", dadosToken);
                intentVaiParaDetalhesComprasReservas.putExtra("usuario", usuario);

                startActivity(intentVaiParaDetalhesComprasReservas);
            }
        });

        botaoNovaReservaVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inserirNovaReserva = new Intent(ListaReservasActivity.this, InserirNovaReservaActivity.class);
                inserirNovaReserva.putExtra("dadosToken", dadosToken);
                startActivity(inserirNovaReserva);
            }
        });

        botaoNovaFeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inserirNovaFeira = new Intent(ListaReservasActivity.this, InserirNovaFeiraActivity.class);
                inserirNovaFeira.putExtra("dadosToken", dadosToken);
                startActivity(inserirNovaFeira);
            }
        });

        botaoParticiparFeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent participarDeFeira = new Intent(ListaReservasActivity.this, ParticiparDeFeiraActivity.class);
                participarDeFeira.putExtra("dadosToken", dadosToken);
                startActivity(participarDeFeira);
            }
        });

    }

    public void setUsuarioOuProdutorLogado(UsuarioLogado usuarioOuProdutorLogado){
        this.usuarioOuProdutorLogado = usuarioOuProdutorLogado;
    }

    public UsuarioLogado getUsuarioOuProdutorLogado(){
        return this.usuarioOuProdutorLogado;
    }
}
