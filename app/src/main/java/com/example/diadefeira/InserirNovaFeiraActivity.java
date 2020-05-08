package com.example.diadefeira;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.task.InserirFeiraTask;
import com.example.diadefeira.task.InserirNovaReservaTask;

public class InserirNovaFeiraActivity extends AppCompatActivity {

    private EditText editTextFeiraNome;
    private EditText editTextFeiraEndereco;
    private EditText editTextFeiraData;
    private Button   botaoSalvarNovaFeira;

    private DadosToken dadosToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_nova_feira);

        editTextFeiraNome = (EditText) findViewById(R.id.activity_inserir_nova_feira_nome);
        editTextFeiraEndereco = (EditText) findViewById(R.id.activity_inserir_nova_feira_endereco);
        editTextFeiraData = (EditText) findViewById(R.id.activity_inserir_nova_feira_data);
        botaoSalvarNovaFeira = (Button) findViewById(R.id.activity_inserir_nova_feira_botao_salvar_feira);

        Intent intent = getIntent();
        dadosToken = (DadosToken) intent.getSerializableExtra("dadosToken");

        botaoSalvarNovaFeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetalhesFeira detalhesFeira = new DetalhesFeira(editTextFeiraNome.getText().toString(),
                                                                        editTextFeiraEndereco.getText().toString(),
                                                                        editTextFeiraData.getText().toString());

                InserirFeiraTask inserirFeiraTask = new InserirFeiraTask(dadosToken, detalhesFeira, InserirNovaFeiraActivity.this);

                inserirFeiraTask.execute();
            }
        });

    }
}
