package com.example.diadefeira;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diadefeira.modelo.DetalhesCompraReserva;
import com.example.diadefeira.task.InserirNovaReservaTask;

public class InserirNovaReservaActivity extends AppCompatActivity {
    private EditText editTextUsuarioEmail;
    private EditText editTextProdutorEmail;
    private EditText editTextIdFeira;
    private EditText editTextListaProdutos;
    private Button botaoSalvarNovaReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_nova_reserva);

        editTextUsuarioEmail = (EditText) findViewById(R.id.activity_inserir_nova_reserva_email_cliente);
        editTextProdutorEmail = (EditText) findViewById(R.id.activity_inserir_nova_reserva_email_produtor);
        editTextIdFeira = (EditText) findViewById(R.id.activity_inserir_nova_reserva_id_feira);
        editTextListaProdutos = (EditText) findViewById(R.id.activity_inserir_nova_reserva_lista_produtos);
        botaoSalvarNovaReserva = (Button) findViewById(R.id.activity_inserir_nova_reserva_botao_salvar_reserva);

        botaoSalvarNovaReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalhesCompraReserva detalhesCompraReserva = new DetalhesCompraReserva();

                detalhesCompraReserva.setUsuarioEmail(editTextUsuarioEmail.getText().toString());
                detalhesCompraReserva.setProdutorEmail(editTextProdutorEmail.getText().toString());
                detalhesCompraReserva.setIdFeira(Long.parseLong(editTextIdFeira.getText().toString()));

                String[] listaCodigosDosProdutos = editTextListaProdutos.getText().toString().split(";");

                InserirNovaReservaTask inserirNovaReservaTask = new InserirNovaReservaTask(detalhesCompraReserva, listaCodigosDosProdutos, InserirNovaReservaActivity.this);

                inserirNovaReservaTask.execute();

                finish();
            }
        });
    }
}