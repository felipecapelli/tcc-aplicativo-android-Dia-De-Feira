package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.modelo.ProdutorLogado;
import com.example.diadefeira.modelo.Usuario;
import com.example.diadefeira.modelo.UsuarioLogado;
import com.example.diadefeira.task.DetalhesComprasReservasTask;
import com.example.diadefeira.task.RealizarVendaTask;

public class DetalhesComprasReservas extends AppCompatActivity {
    private DadosToken dadosToken;
    private TextView TextViewIdCompraReserva;
    private TextView TextViewUsuarioEmail;
    private EditText editTextUsuarioNome;
    private TextView TextViewProdutorEmail;
    private EditText editTextProdutorNome;
    private TextView TextViewIdFeira;
    private EditText editTextNomeFeira;
    private EditText editTextEnderecoFeira;
    private EditText editTextDataFeira;
    private EditText editTextDataReserva;
    private EditText editTextDataVenda;
    private TextView textViewDataVendaTitulo;
    private Button buttonVender;

    private ListView listViewListaDeProdutos;

    private UsuarioLogado usuarioLogado;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_compras_reservas);

        Intent intent = getIntent();
        Long idCompraReserva = (Long) intent.getSerializableExtra("idCompraReserva");
        dadosToken = (DadosToken) intent.getSerializableExtra("dadosToken") ;
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        usuarioLogado = (UsuarioLogado) intent.getSerializableExtra("usuarioLogado");

        buttonVender = (Button) findViewById(R.id.activity_detalhes_compras_reservas_botao_vender);
        try{
            ProdutorLogado produtorLogado = (ProdutorLogado) usuarioLogado;
            buttonVender.setVisibility(View.VISIBLE);
        } catch (ClassCastException e) {}

        TextViewIdCompraReserva = (TextView) findViewById(R.id.activity_detalhes_compras_reservas_id_compra_reserva);
        TextViewUsuarioEmail = (TextView) findViewById(R.id.activity_detalhes_compras_reservas_email_cliente);
        editTextUsuarioNome = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_campo_nome_cliente);
        editTextUsuarioNome.setFocusable(false);
        TextViewProdutorEmail = (TextView) findViewById(R.id.activity_detalhes_compras_reservas_email_produtor);
        editTextProdutorNome = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_campo_nome_produtor);
        editTextProdutorNome.setFocusable(false);
        TextViewIdFeira = (TextView) findViewById(R.id.activity_detalhes_compras_reservas_id_feira);
        editTextNomeFeira = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_campo_nome_feira);
        editTextNomeFeira.setFocusable(false);
        editTextEnderecoFeira = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_campo_endereco_feira);
        editTextEnderecoFeira.setFocusable(false);
        editTextDataFeira = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_campo_data_feira);
        editTextDataFeira.setFocusable(false);
        editTextDataReserva = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_data_reserva);
        editTextDataReserva.setFocusable(false);
        editTextDataVenda = (EditText) findViewById(R.id.activity_detalhes_compras_reservas_data_venda);
        editTextDataVenda.setFocusable(false);
        textViewDataVendaTitulo = (TextView) findViewById(R.id.activity_detalhes_compras_reservas_titulo_data_venda);//esse é só o rótulo

        listViewListaDeProdutos = (ListView) findViewById(R.id.activity_detalhes_compras_reservas_lista_produtos);

        DetalhesComprasReservasTask detalhesComprasReservasTask = new DetalhesComprasReservasTask(dadosToken, idCompraReserva,
                TextViewIdCompraReserva, TextViewUsuarioEmail, editTextUsuarioNome, TextViewProdutorEmail,
                editTextProdutorNome, TextViewIdFeira, editTextNomeFeira, editTextEnderecoFeira, editTextDataFeira,
                editTextDataReserva, editTextDataVenda, textViewDataVendaTitulo, listViewListaDeProdutos, DetalhesComprasReservas.this);
        detalhesComprasReservasTask.execute();

        buttonVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealizarVendaTask realizarVendaTask = new RealizarVendaTask(Long.parseLong(TextViewIdCompraReserva.getText().toString()), dadosToken, DetalhesComprasReservas.this);
                realizarVendaTask.execute();
            }
        });

    }
}
