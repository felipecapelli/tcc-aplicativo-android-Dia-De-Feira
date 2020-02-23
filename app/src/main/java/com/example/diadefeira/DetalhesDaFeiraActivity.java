package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.modelo.DetalhesFeiraProduto;
import com.example.diadefeira.modelo.DetalhesFeiraProdutor;
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

            DetalhesFeiraTask detalhesFeiraTask = new DetalhesFeiraTask(feira.getId(), listaProdutos, listaProdutores, DetalhesDaFeiraActivity.this);
            detalhesFeiraTask.execute();
        }

        //Clicar em um dos produtos
        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                if (feira != null){
                    DetalhesFeiraProduto detalhesFeiraProduto = new DetalhesFeiraProduto();
                    TextView textViewIdProduto = item.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_id_produto);
                    detalhesFeiraProduto.setId(Long.parseLong(textViewIdProduto.getText().toString()));

                    TextView textViewNomeProduto = item.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_nome_produto);
                    detalhesFeiraProduto.setNome(textViewNomeProduto.getText().toString());

                    Intent intentVaiParaDetalhesDoProduto = new Intent(DetalhesDaFeiraActivity.this, DetalhesDoProdutoActivity.class);
                    intentVaiParaDetalhesDoProduto.putExtra("feira", feira);

                    intentVaiParaDetalhesDoProduto.putExtra("produto", detalhesFeiraProduto);
                    startActivity(intentVaiParaDetalhesDoProduto);
                }
            }
        });

        //Clicar em um dos produtores
        listaProdutores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                if (feira != null){
                    DetalhesFeiraProdutor detalhesFeiraProdutor = new DetalhesFeiraProdutor();

                    TextView textViewEmailProdutor = item.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_email_produtor);
                    detalhesFeiraProdutor.setEmail(textViewEmailProdutor.getText().toString());

                    TextView textViewNomeProdutor = item.findViewById(R.id.activity_detalhes_da_feira_lista_produtores_nome_produtor);
                    detalhesFeiraProdutor.setNome(textViewNomeProdutor.getText().toString());

                    Intent intentVaiParaDetalhesDoProdutor = new Intent(DetalhesDaFeiraActivity.this, DetalhesDoProdutorActivity.class);
                    intentVaiParaDetalhesDoProdutor.putExtra("feira", feira);

                    intentVaiParaDetalhesDoProdutor.putExtra("produtor", detalhesFeiraProdutor);
                    startActivity(intentVaiParaDetalhesDoProdutor);
                }
            }
        });
    }
}
