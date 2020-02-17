package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.task.ProdutoresTask;

public class ListaProdutoresActivity extends AppCompatActivity {

    private TextView textoNomeProdutorBusca;
    private ListView listViewFeiras;
    private ListView listViewProdutos;
    private Button botaoBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtores);

        textoNomeProdutorBusca = (TextView) findViewById(R.id.activity_lista_produtores_texto_nome_produtor);
        botaoBuscar = (Button) findViewById(R.id.activity_lista_produtores_botao_busca);
        listViewFeiras = (ListView) findViewById(R.id.activity_lista_produtores_lista_feiras);
        listViewProdutos = (ListView) findViewById(R.id.activity_lista_produtores_lista_produtos);

        textoNomeProdutorBusca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textoNomeProdutorBusca.setText("");
            }
        });

        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdutoresTask produtoresTask = new ProdutoresTask(textoNomeProdutorBusca.getText().toString(), listViewFeiras, listViewProdutos, ListaProdutoresActivity.this);
                produtoresTask.execute();
            }
        });
    }
}
