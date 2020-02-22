package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.modelo.DetalhesFeiraProduto;
import com.example.diadefeira.task.DetalhesProdutoTask;

public class DetalhesDoProdutoActivity extends AppCompatActivity {
    private DetalhesFeira feira;
    private DetalhesFeiraProduto produto;


    private TextView textViewIdFeira;
    private TextView textViewNomeFeira;
    private TextView textViewEnderecoFeira;
    private TextView textViewDataFeira;
    private ListView listaDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_produto);

        Intent intent = getIntent();
        feira = (DetalhesFeira) intent.getSerializableExtra("feira");
        produto = (DetalhesFeiraProduto) intent.getSerializableExtra("produto");

        textViewIdFeira = (TextView) findViewById(R.id.activity_detalhes_do_produto_id_feira);
        textViewNomeFeira = (TextView) findViewById(R.id.activity_detalhes_do_produto_nome_feira);
        textViewEnderecoFeira = (TextView) findViewById(R.id.activity_detalhes_do_produto_endereco_feira);
        textViewDataFeira = (TextView) findViewById(R.id.activity_detalhes_do_produto_data_feira);

        listaDetalhes = (ListView) findViewById(R.id.activity_detalhes_do_produto_lista_detalhes);

        textViewIdFeira.setText(String.valueOf(feira.getId()));
        textViewNomeFeira.setText(feira.getNome());
        textViewEnderecoFeira.setText(feira.getEndereco());
        textViewDataFeira.setText(feira.getData());

        DetalhesProdutoTask detalhesFeiraTask = new DetalhesProdutoTask(feira.getId(), produto, listaDetalhes, DetalhesDoProdutoActivity.this);
        detalhesFeiraTask.execute();
    }
}
