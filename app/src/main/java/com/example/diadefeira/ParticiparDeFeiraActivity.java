package com.example.diadefeira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diadefeira.modelo.DadosToken;
import com.example.diadefeira.modelo.ParticiparDeFeira;
import com.example.diadefeira.task.InserirFeiraTask;
import com.example.diadefeira.task.ParticiparDeFeiraTask;

public class ParticiparDeFeiraActivity extends AppCompatActivity {

    private EditText editTextEmailProdutor;
    private EditText editTextIdFeira;
    private EditText editTextListaProdutor;
    private EditText editTextListaPrecos;
    private Button buttonSalvarParticipacaoFeira;

    private DadosToken dadosToken;
    private ParticiparDeFeira participarDeFeira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participar_de_feira);

        editTextEmailProdutor = (EditText) findViewById(R.id.activity_participar_de_feira_email_produtor);
        editTextIdFeira = (EditText) findViewById(R.id.activity_participar_de_feira_id_feira);
        editTextListaProdutor = (EditText) findViewById(R.id.activity_participar_de_feira_lista_produtos);
        editTextListaPrecos = (EditText) findViewById(R.id.activity_participar_de_feira_lista_precos);
        buttonSalvarParticipacaoFeira = (Button) findViewById(R.id.activity_participar_de_feira_botao_participar_feira);

        Intent intent = getIntent();
        dadosToken = (DadosToken) intent.getSerializableExtra("dadosToken") ;

        buttonSalvarParticipacaoFeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participarDeFeira = new ParticiparDeFeira();
                participarDeFeira.setEmailProdutor(editTextEmailProdutor.getText().toString());
                participarDeFeira.setIdFeira(Long.parseLong(editTextIdFeira.getText().toString()));
                participarDeFeira.setListaProdutos(editTextListaProdutor.getText().toString().split(";"));
                participarDeFeira.setListaPrecos(editTextListaPrecos.getText().toString().split(";"));

                ParticiparDeFeiraTask participarDeFeiraTask = new ParticiparDeFeiraTask(dadosToken, participarDeFeira, ParticiparDeFeiraActivity.this);
                participarDeFeiraTask.execute();
            }
        });

    }
}
