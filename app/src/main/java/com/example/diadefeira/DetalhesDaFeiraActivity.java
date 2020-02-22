package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.task.DetalhesFeiraTask;

public class DetalhesDaFeiraActivity extends AppCompatActivity {

    private DetalhesFeira feira;
    private TextView nomeFeira;
    private TextView localFeira;
    private TextView dataFeira;
    private ListView listaProdutos;
    private ListView listaProdutores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_da_feira);

        Intent intent = getIntent();
        feira = (DetalhesFeira) intent.getSerializableExtra("feira");

        nomeFeira = findViewById(R.id.activity_detalhes_da_feira_nome_feira);
        localFeira = findViewById(R.id.activity_detalhes_da_feira_local_feira);
        dataFeira = findViewById(R.id.activity_detalhes_da_feira_data_feira);

        listaProdutos = findViewById(R.id.activity_detalhes_da_feira_lista_produtos);
        listaProdutores = findViewById(R.id.activity_detalhes_da_feira_lista_produtores);

        if (feira != null){
            nomeFeira.setText(feira.getNome());
            localFeira.setText(feira.getEndereco());
            dataFeira.setText(feira.getData());

            System.out.println("teste --------------------------------------");
            DetalhesFeiraTask detalhesFeiraTask = new DetalhesFeiraTask(feira.getId(), listaProdutos, listaProdutores, DetalhesDaFeiraActivity.this);
            detalhesFeiraTask.execute();
        }

    }
}
