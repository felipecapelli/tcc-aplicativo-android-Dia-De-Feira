package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.modelo.DetalhesFeiraProdutor;
import com.example.diadefeira.task.DetalhesProdutorTask;

public class DetalhesDoProdutorActivity extends AppCompatActivity {
    private DetalhesFeira feira;
    private DetalhesFeiraProdutor produtor;

    private TextView textViewEmailProdutor;
    private TextView textViewNomeProdutor;
    private TextView textViewIdFeira;
    private TextView textViewNomeFeira;
    private TextView textViewEnderecoFeira;
    private TextView textViewDataFeira;
    private ListView listViewDetalhes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_produtor);

        Intent intent = getIntent();
        feira = (DetalhesFeira) intent.getSerializableExtra("feira");
        produtor = (DetalhesFeiraProdutor) intent.getSerializableExtra("produtor");

        textViewEmailProdutor = findViewById(R.id.activity_detalhes_do_produtor_email_produtor);
        textViewNomeProdutor = findViewById(R.id.activity_detalhes_do_produtor_nome_produtor);
        textViewIdFeira = findViewById(R.id.activity_detalhes_do_produtor_id_feira);
        textViewNomeFeira = findViewById(R.id.activity_detalhes_do_produtor_nome_feira);
        textViewEnderecoFeira = findViewById(R.id.activity_detalhes_do_produtor_endereco_feira);
        textViewDataFeira = findViewById(R.id.activity_detalhes_do_produtor_data_feira);

        listViewDetalhes = findViewById(R.id.activity_detalhes_do_produtor_lista_detalhes);

        textViewEmailProdutor.setText(produtor.getEmail());
        textViewNomeProdutor.setText(produtor.getNome());
        textViewIdFeira.setText(feira.getId().toString());
        textViewNomeFeira.setText(feira.getNome());
        textViewEnderecoFeira.setText(feira.getEndereco());
        textViewDataFeira.setText(feira.getData());

        DetalhesProdutorTask detalhesProdutorTask = new DetalhesProdutorTask(produtor.getEmail(), feira.getId(), listViewDetalhes, DetalhesDoProdutorActivity.this);
        detalhesProdutorTask.execute();
    }
}
